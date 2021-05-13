package com.example.userscreeningsuhu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton blogin, bregister, btnAdmin;
    AppCompatEditText txUsername, txPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txUsername = (AppCompatEditText) findViewById(R.id.username);
        txPassword = (AppCompatEditText) findViewById(R.id.password);
        blogin = (AppCompatButton) findViewById(R.id.btn_login);
        bregister = (AppCompatButton) findViewById(R.id.btn_signup);
        btnAdmin = (AppCompatButton) findViewById(R.id.btn_admin);

        final LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

        blogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String trueUsername = Objects.requireNonNull(txUsername.getText().toString().trim());
                String truePassword = Objects.requireNonNull(txPassword.getText().toString().trim());

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

                Query checkUser = reference.orderByChild("username").equalTo(trueUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){

                            txUsername.setError(null);

                            String passwordFromDB = snapshot.child(trueUsername).child("password").getValue(String.class);

                            if (passwordFromDB != null && passwordFromDB.equals(truePassword)) {

                                txUsername.setError(null);

                                String nameFromDB = snapshot.child(trueUsername).child("name").getValue(String.class);
                                String phoneFromDB = snapshot.child(trueUsername).child("phone").getValue(String.class);
                                String statusFromDB = snapshot.child(trueUsername).child("status").getValue(String.class);
                                String usernameFromDB = snapshot.child(trueUsername).child("username").getValue(String.class);
                                String alamatFromDB = snapshot.child(trueUsername).child("alamat").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                                intent.putExtra("name", nameFromDB);
                                intent.putExtra("phone", phoneFromDB);
                                intent.putExtra("status", statusFromDB);
                                intent.putExtra("username", usernameFromDB);
                                intent.putExtra("password", passwordFromDB);
                                intent.putExtra("alamat", alamatFromDB);

                                loadingDialog.startLoadingDialog();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismissDialog();
                                        startActivity(intent);
                                    }
                                }, 3000);
                            }
                            else {
                                txPassword.setError("Password Salah");
                            }

                        } else {
                            txUsername.setError("Username Tidak Ditemukan");
                        }



                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    }
                }, 3000);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(LoginActivity.this, adminLogin.class));
                    }
                }, 3000);
            }
        });
    }



}