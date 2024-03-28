package com.example.goodwillapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class Welcome extends AppCompatActivity {

    ImageView id_logout ;
    ShapeableImageView id_beneficiary ,id_contributor;
    LinearLayout id_layoutBene,layoutCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        id_logout = findViewById(R.id.id_logout);
        id_layoutBene = findViewById(R.id.id_layoutBene);
        id_contributor = findViewById(R.id.id_contributor);
        layoutCont = findViewById(R.id.layoutCont);
        id_beneficiary = findViewById(R.id.id_beneficiary);


        id_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Signin.class);
                startActivity(intent);
                finish();
            }
        });
        layoutCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callContributors();
            }
        });

        id_contributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callContributors();
            }
        });


        id_beneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callBeneficiary();
            }
        });

        id_layoutBene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBeneficiary();
            }
        });


    }

    private void callBeneficiary() {
        Intent intent = new Intent(Welcome.this, Contributor.class);
        startActivity(intent);
    }

    private void callContributors() {
        Intent intent = new Intent(Welcome.this, Beneficiary.class);
        startActivity(intent);
    }
}