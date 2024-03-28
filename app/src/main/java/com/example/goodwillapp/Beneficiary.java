package com.example.goodwillapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Beneficiary extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);

        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            items.add("Item " + i);
        }

          recyclerView = findViewById(R.id.id_availableList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        AvailableAdapter adapter = new AvailableAdapter(Beneficiary.this);
        recyclerView.setAdapter(adapter);
    }
}