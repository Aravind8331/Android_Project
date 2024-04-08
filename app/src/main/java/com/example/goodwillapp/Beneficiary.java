package com.example.goodwillapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.goodwillapp.common.ContributorModel;
import com.example.goodwillapp.common.ItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Beneficiary extends AppCompatActivity {
    private DatabaseReference databaseRef;
    ArrayList<ItemModel> itemList;
    List<ContributorModel> items = new ArrayList<>();
    RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    AvailableAdapter adapter;
    Button bt_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);
        bt_spinner = findViewById(R.id.bt_spinner);
        itemList = new ArrayList<>();
         itemList.add(new ItemModel("Electronics", 1));
        itemList.add(new ItemModel("HouseholdItems", 2));
        itemList.add(new ItemModel("Food", 3));
        itemList.add(new ItemModel("Other", 4));

        databaseRef = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.id_availableList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AvailableAdapter(Beneficiary.this,items);
        recyclerView.setAdapter(adapter);
        retrieveData("ALL");

        bt_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(Beneficiary.this, bt_spinner);
                if (itemList != null) {
                    for (int y = 0; y < itemList.size(); y++) {
                        menu.getMenu().add(itemList.get(y).getItemName());
                    }
                }
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        bt_spinner.setText(item.getTitle());
                        retrieveData(item.getTitle().toString());

                        return false;
                    }
                });
            }
        });
    }

    private void retrieveData(String filterValue) {
        progressDialog = new ProgressDialog(Beneficiary.this);
        progressDialog.setMessage("Retriving Details...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        DatabaseReference newDataRef = databaseRef.child("contributors") ;
        newDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();

                items.clear();
                for (DataSnapshot snapshotss : snapshot.getChildren()) {
                    ContributorModel user = snapshotss.getValue(ContributorModel.class);
                    if (user != null) {
                       if(filterValue=="ALL"){
                           items.add(user);
                       }else{
                           if(user.getItemType().equals(filterValue)){
                               items.add(user);
                           }
                       }


                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(Beneficiary.this, "DatabaseError", Toast.LENGTH_SHORT).show();
            }
        });

    /*    newDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("MainActivity", "Failed to read value.", databaseError.toException());
            }
        });*/
    }
}