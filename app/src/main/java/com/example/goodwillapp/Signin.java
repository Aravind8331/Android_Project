package com.example.goodwillapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.goodwillapp.common.GmailValidator;
import com.google.android.material.textfield.TextInputEditText;

public class Signin extends AppCompatActivity {

    LinearLayout id_createAccount;
    Button loginButton;
    TextInputEditText id_email,id_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_createAccount = findViewById(R.id.id_createAccount);
        loginButton = findViewById(R.id.loginButton);
        id_email = findViewById(R.id.id_email);
        id_password = findViewById(R.id.id_password);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = id_email.getText().toString().trim();
                String password = id_password.getText().toString().trim();

                emailID = "Abcd@gmail.com";
                password = "Abcd@1234";

                if(!emailID.isEmpty()){
                    if(!password.isEmpty()){
                        if(GmailValidator.isValidGmail(emailID)){
                            if(GmailValidator.isValidPassword(password)){
                                checkLogin(emailID,password);
                            }else{
                                Toast.makeText(Signin.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Signin.this, "Enter Valid Gmail ID", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Signin.this, "Enter Gmail ID", Toast.LENGTH_SHORT).show();
                }

            }
        });

        id_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin(String emailID, String password) {

        Intent intent = new Intent(Signin.this, Welcome.class);
        startActivity(intent);
    }
}