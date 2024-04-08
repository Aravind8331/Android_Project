package com.example.goodwillapp;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodwillapp.common.ContributorModel;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
public class AvailableAdapter extends RecyclerView.Adapter<AvailableAdapter.ViewHolder> {

    private List<ContributorModel> dataList;
    Beneficiary beneficiaryss;
    public AvailableAdapter(Beneficiary beneficiary, List<ContributorModel> dataList) {
        this.dataList = dataList;
        this.beneficiaryss = beneficiary;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position).getItemName());
        holder.id_address.setText(dataList.get(position).getAddress());
        holder.id_name.setText(dataList.get(position).getContributorName());
        if(dataList.get(position).getItemImage() != null && !(dataList.get(position).getItemImage().isEmpty())){
            Picasso.get().load(dataList.get(position).getItemImage()).into(holder.id_beneficiary);
        }


        holder.id_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beneficiaryss, ContributorDetailsActivity.class);
                intent.putExtra("dataModel", dataList.get(position));
                beneficiaryss.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView,id_name,id_address;
        private ShapeableImageView id_beneficiary;
        Button id_upload;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.id_itenamerec);
            id_name = itemView.findViewById(R.id.id_name);
            id_address = itemView.findViewById(R.id.id_address);
            id_upload = itemView.findViewById(R.id.id_upload);
            id_beneficiary = itemView.findViewById(R.id.id_beneficiary);
        }
    }
}