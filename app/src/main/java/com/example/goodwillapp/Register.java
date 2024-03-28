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

public class Register extends AppCompatActivity {

    Button id_register;
    LinearLayout id_loginAccount;
    TextInputEditText id_username,id_email,id_password,id_confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id_register = findViewById(R.id.id_register);
        id_loginAccount = findViewById(R.id.id_loginAccount);
        id_username = findViewById(R.id.id_username);
        id_email = findViewById(R.id.id_email);
        id_password = findViewById(R.id.id_password);
        id_confirmpassword = findViewById(R.id.id_confirmpassword);


        id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailID = id_email.getText().toString().trim();
                String username = id_username.getText().toString().trim();
                String password = id_password.getText().toString().trim();
                String confirmpassword = id_confirmpassword.getText().toString().trim();

                if(!username.isEmpty()){
                    if(!emailID.isEmpty()){
                        if(!password.isEmpty()){

                            if(!confirmpassword.isEmpty()){
                                if(password == confirmpassword){
                                    if(GmailValidator.isValidGmail(emailID)){
                                        if(GmailValidator.isValidPassword(password)){
                                            saveDetails(emailID,password,username);
                                        }else{
                                            Toast.makeText(Register.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(Register.this, "Enter Valid Gmail ID", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(Register.this, "Password Mismatch", Toast.LENGTH_SHORT).show();

                                }
                            }else{
                                Toast.makeText(Register.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                            }

                        }else{
                            Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Enter Gmail ID", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Register.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }




            }
        });

        id_loginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Signin.class);
                startActivity(intent);
            }
        });

    }

    private void saveDetails(String emailID, String password, String username) {
        Intent intent = new Intent(Register.this, Welcome.class);
        startActivity(intent);
    }
}