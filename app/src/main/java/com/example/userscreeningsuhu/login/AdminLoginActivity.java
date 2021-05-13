package com.example.userscreeningsuhu.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.R;
import com.example.userscreeningsuhu.history.RiwayatAdminActivity;

import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class AdminLoginActivity extends AppCompatActivity {

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

        final LoadingDialog loadingDialog = new LoadingDialog(AdminLoginActivity.this);

        btnMasuk.setOnClickListener(v -> {
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
                    Intent intent = new Intent(AdminLoginActivity.this, RiwayatAdminActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        loadingDialog.dismissDialog();
                        startActivity(intent);
                    }, 1000);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Admin atau Password Salah!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnUser.setOnClickListener(v -> startActivity(new Intent(AdminLoginActivity.this, LoginActivity.class)));
    }
}