package com.example.goodwillapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.goodwillapp.common.ContributorModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelcfActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ContributorModel> items = new ArrayList<>();
    private ProgressDialog progressDialog;

    SelfAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selcf);

        Intent intent = getIntent();
        String userType = intent.getStringExtra("SELFCONTRIBUTOR");


        recyclerView = findViewById(R.id.id_selfrecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelfAdapter(SelcfActivity.this,items);
        recyclerView.setAdapter(adapter);
        if(userType.equals("CONTRIBUTOR")){
            retrieveData("contributorSelf");
        }else if(userType.equals("BENEFICIAR")) {
            retrieveData("beneficiarisSelf");
        }else{
            retrieveData("contributorSelf");
        }

    }

    private void retrieveData(String usertype) {
        progressDialog = new ProgressDialog(SelcfActivity.this);
        progressDialog.setMessage("Retriving Details...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference newDataRef = databaseRef.child(usertype).child(userId) ;
        newDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();

                items.clear();
                for (DataSnapshot snapshotss : snapshot.getChildren()) {
                    ContributorModel user = snapshotss.getValue(ContributorModel.class);
                    if (user != null) {
                        items.add(user);
                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(SelcfActivity.this, "DatabaseError", Toast.LENGTH_SHORT).show();
            }
        });

    }
}