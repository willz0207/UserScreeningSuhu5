package com.example.userscreeningsuhu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class adminLogin extends AppCompatActivity {

    AppCompatEditText adminUsername, adminPassword;
    AppCompatButton btnMasuk, btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUsername = findViewById(R.id.adminUsername);
        adminPassword = findViewById(R.id.adminPassword);
        btnMasuk = findViewById(R.id.lgn_admin);
        btnUser = findViewById(R.id.lgn_user);

        final LoadingDialog loadingDialog = new LoadingDialog(adminLogin.this);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin = Objects.requireNonNull(adminUsername.getText()).toString();
                String password = Objects.requireNonNull(adminPassword.getText()).toString();

                if (admin.isEmpty()){
                    adminUsername.setError("Admin Tidak Boleh Kosong!");
                    adminUsername.requestFocus();
                }
                else if (password.isEmpty()){
                    adminPassword.setError("Password Tidak Boleh Kosong");
                    adminPassword.requestFocus();
                } else {
                    if (admin.equals("Admin") && password.equals("admin")){
                        Intent intent = new Intent(adminLogin.this, RiwayatAdmin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                startActivity(intent);
                            }
                        }, 3000);
                    } else {
                        Toast.makeText(adminLogin.this, "Admin atau Password Salah!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(adminLogin.this, LoginActivity.class));
                    }
                }, 3000);
            }
        });
    }
}