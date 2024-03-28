package com.example.goodwillapp;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class AvailableAdapter extends RecyclerView.Adapter<AvailableAdapter.ViewHolder> {

    // Define your data list here
    private List<String> dataList;
    Beneficiary beneficiary;
    public AvailableAdapter(Beneficiary beneficiarys) {
        beneficiary=beneficiarys;
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("Item " + (i + 1));
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position));

        holder.id_beneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beneficiary, ContributorDetailsActivity.class);
                beneficiary.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // ViewHolder class to hold the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ShapeableImageView id_beneficiary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.id_itenamerec);
            id_beneficiary = itemView.findViewById(R.id.id_beneficiary);
        }

        public void bind(String item) {
            textView.setText(item);
        }
    }
}
