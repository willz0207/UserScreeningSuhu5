package com.example.userscreeningsuhu.login;

import android.content.Intent;
import android.os.Bundle;

import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.MainActivity;
import com.example.userscreeningsuhu.R;
import com.example.userscreeningsuhu.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton blogin, bregister, btnAdmin;
    AppCompatEditText txUsername, txPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txUsername = findViewById(R.id.username);
        txPassword = findViewById(R.id.password);
        blogin = findViewById(R.id.btn_login);
        bregister = findViewById(R.id.btn_signup);
        btnAdmin = findViewById(R.id.btn_admin);

        final LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

        blogin.setOnClickListener(v -> {
            String trueUsername = Objects.requireNonNull(txUsername.getText().toString().trim());
            String truePassword = Objects.requireNonNull(txPassword.getText().toString().trim());

            loadingDialog.startLoadingDialog();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild("username").equalTo(trueUsername);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    loadingDialog.dismissDialog();
                    if (snapshot.exists()) {

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
                            startActivity(intent);
                        } else {
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
        });

        bregister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        btnAdmin.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, AdminLoginActivity.class)));
    }
}