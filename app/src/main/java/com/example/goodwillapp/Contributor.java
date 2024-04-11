package com.example.goodwillapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodwillapp.common.ContributorModel;
import com.example.goodwillapp.common.GmailValidator;
import com.example.goodwillapp.common.ItemModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Contributor extends AppCompatActivity {

    TextInputEditText id_itemName, id_name, id_mobile, id_email, id_address;
    Button bt_spinner, bt_condition, id_save, id_upload;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private DatabaseReference databaseRef;
    private DatabaseReference databaseRefSelf;
    ImageView id_back;

    String imagePath = "";
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int IMAGE_QUALITY = 100;
    private ProgressDialog progressDialog;

    ArrayList<ItemModel> itemList;
    ArrayList<ItemModel> itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);
        initvalues();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRefSelf = FirebaseDatabase.getInstance().getReference();

        itemList = new ArrayList<>();
        itemType = new ArrayList<>();

        itemList.add(new ItemModel("Electronics", 1));
        itemList.add(new ItemModel("HouseholdItems", 2));
        itemList.add(new ItemModel("Food", 3));
        itemList.add(new ItemModel("Other", 4));


        itemType.add(new ItemModel("Good", 5));
        itemType.add(new ItemModel("Medium", 6));
        itemType.add(new ItemModel("Poor", 7));


        id_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPermission();
            }
        });

        id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contributor.this, Welcome.class);
                startActivity(intent);
                finish();
            }
        });


        bt_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(Contributor.this, bt_spinner);
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

                        for (int y = 0; y < itemList.size(); y++) {
                            if (bt_spinner.getText().toString().equalsIgnoreCase(itemList.get(y).getItemName())) {
                                bt_spinner.setTag(itemList.get(y).getItemID());
                            }

                        }


                        return false;
                    }
                });
            }
        });
        bt_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(Contributor.this, bt_condition);
                if (itemType != null) {
                    for (int y = 0; y < itemType.size(); y++) {
                        menu.getMenu().add(itemType.get(y).getItemName());
                    }
                }
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        bt_condition.setText(item.getTitle());

                        for (int y = 0; y < itemType.size(); y++) {
                            if (bt_condition.getText().toString().equalsIgnoreCase(itemType.get(y).getItemName())) {
                                bt_condition.setTag(itemType.get(y).getItemID());
                            }

                        }


                        return false;
                    }
                });
            }
        });

        id_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailID = id_email.getText().toString().trim();
                String username = id_name.getText().toString().trim();
                String mobileNumber = id_mobile.getText().toString().trim();
                String itemName = id_itemName.getText().toString().trim();
                String address = id_address.getText().toString().trim();
                String itemType = bt_spinner.getText().toString().trim();
                String itemCondition = bt_condition.getText().toString().trim();

                if (!itemName.isEmpty()) {
                    if (!address.isEmpty()) {
                        if (!emailID.isEmpty()) {
                            if (!username.isEmpty()) {
                                if (!mobileNumber.isEmpty()) {

                                    if (bt_spinner.getTag() != "11") {
                                        if (bt_condition.getTag() != "11") {

                                            if (GmailValidator.isValidGmail(emailID)) {
                                                if (GmailValidator.isValidPhoneNumber(mobileNumber)) {
                                                    saveContributor(emailID, username, mobileNumber, itemName, address, itemType, itemCondition);
                                                } else {
                                                    Toast.makeText(Contributor.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(Contributor.this, "Enter Valid Mail ID", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(Contributor.this, "Select Item Condition", Toast.LENGTH_SHORT).show();

                                        }


                                    } else {
                                        Toast.makeText(Contributor.this, "Select Item Type", Toast.LENGTH_SHORT).show();

                                    }

                                } else {
                                    Toast.makeText(Contributor.this, "Enter MobileNumber", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Contributor.this, "Enter UserName", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Contributor.this, "Enter MailID", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Contributor.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Contributor.this, "Enter Item Name", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void saveContributor(String emailID, String username, String mobileNumber, String itemName, String address, String itemType, String itemCondition) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        progressDialog = new ProgressDialog(Contributor.this);
        progressDialog.setMessage("Saving Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        DatabaseReference newDataRef = databaseRef.child("contributors").push();
        Log.d("USERKEY", newDataRef.getKey().toString());
        String dbKey = newDataRef.getKey();
        DatabaseReference selfContributor = databaseRefSelf.child("contributorSelf").child(userId).push();

        ContributorModel model = new ContributorModel(username, itemName, itemType, itemCondition, imagePath, emailID, address, mobileNumber, dbKey);

        newDataRef.setValue(model)
                .addOnSuccessListener(aVoid -> {

                    selfContributor.setValue(model)
                            .addOnSuccessListener(aVoids -> {
                                progressDialog.dismiss();
                                Toast.makeText(Contributor.this, "Data added ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Contributor.this, Welcome.class);
                                startActivity(intent);
                                finish();

                            })
                            .addOnFailureListener(e -> {
                                progressDialog.dismiss();

                                Toast.makeText(Contributor.this, "Saving Failed", Toast.LENGTH_SHORT).show();
                            });


                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    // Handle any errors adding the data
                    Toast.makeText(Contributor.this, "Saving Failed", Toast.LENGTH_SHORT).show();
                });
    }


    private void cameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {

            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            // If request is cancelled, the grantResults array is empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {

                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            uploadImageToFirebase(imageBitmap);
        }
    }

    private void uploadImageToFirebase(Bitmap bitmap) {
        progressDialog = new ProgressDialog(Contributor.this);
        progressDialog.setMessage("Uploading Image...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_QUALITY, baos);
        byte[] imageData = baos.toByteArray();

        // Generate a unique filename based on the current timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String filename = "image_" + timeStamp + ".jpg";

        StorageReference imageRef = storageRef.child("images/" + filename);

        imageRef.putBytes(imageData)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully
                    id_upload.setText("Image Uploaded");
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        imagePath = uri.toString();
                        progressDialog.dismiss();

                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();

                        Toast.makeText(Contributor.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                    });

                })
                .addOnFailureListener(exception -> {
                    progressDialog.dismiss();

                    Toast.makeText(Contributor.this, "Failed to upload image to Firebase Storage", Toast.LENGTH_SHORT).show();
                });
    }


    private void initvalues() {
        id_mobile = findViewById(R.id.id_mobile);
        id_back = findViewById(R.id.id_back);
        id_name = findViewById(R.id.id_name);
        id_email = findViewById(R.id.id_email);
        id_itemName = findViewById(R.id.id_itemName);
        id_address = findViewById(R.id.id_address);
        bt_spinner = findViewById(R.id.bt_spinner);
        id_save = findViewById(R.id.id_save);
        id_upload = findViewById(R.id.id_upload);
        bt_condition = findViewById(R.id.bt_condition);

    }

}
