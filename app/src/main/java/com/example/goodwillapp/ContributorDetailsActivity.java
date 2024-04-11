package com.example.goodwillapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodwillapp.common.ContributorModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContributorDetailsActivity extends AppCompatActivity {

    TextView name,number,gmail,address,itemname,itemtype;
    ContributorModel dataModel;
    Button id_register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor_details);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        id_register = findViewById(R.id.id_register);
        gmail = findViewById(R.id.gmail);
        address = findViewById(R.id.address);
        itemname = findViewById(R.id.itemname);
        itemtype = findViewById(R.id.itemtype);

        try{
            Intent intent = getIntent();
            dataModel = (ContributorModel) intent.getSerializableExtra("dataModel");

            if(dataModel!=null){

                name.setText(dataModel.getContributorName());
                number.setText(dataModel.getMobileNumber());
                gmail.setText(dataModel.getEmailID());
                itemtype.setText(dataModel.getItemType());
                address.setText(dataModel.getAddress());
                itemname.setText(dataModel.getItemName());

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ContributorDetailsActivity.this);
                progressDialog.setMessage("Collecting Item...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                FirebaseAuth mAuth =FirebaseAuth.getInstance();
                String userId = mAuth.getCurrentUser().getUid();

                DatabaseReference databaseRef =FirebaseDatabase.getInstance().getReference();
                DatabaseReference newDataRef = databaseRef.child("beneficiarisSelf").child(userId).push();
                String dbKey = newDataRef.getKey();


                DatabaseReference databaseRef2 =FirebaseDatabase.getInstance().getReference();
                DatabaseReference deleteRef = databaseRef2.child("contributors");


                String oldKey = dataModel.getDbKey();

                dataModel.setDbKey(dbKey);

                if(dataModel != null){
                    newDataRef.setValue(dataModel)
                            .addOnSuccessListener(aVoids -> {
                                deleteRef.child(oldKey).removeValue();
                                progressDialog.dismiss();
                                Toast.makeText(ContributorDetailsActivity.this, "Successfully Collected ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ContributorDetailsActivity.this, Welcome.class);
                                startActivity(intent);
                                finish();

                            })
                            .addOnFailureListener(e -> {
                                progressDialog.dismiss();

                                Toast.makeText(ContributorDetailsActivity.this, "Saving Failed", Toast.LENGTH_SHORT).show();
                            });
                }



            }
        });
    }
}