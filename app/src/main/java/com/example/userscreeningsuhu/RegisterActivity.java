package com.example.userscreeningsuhu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.helper.UserHelperClass;
import com.example.userscreeningsuhu.login.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;

public class RegisterActivity extends AppCompatActivity {

    //Variable
    AppCompatEditText edtInputName, edtInputPhone, edtInputAlamat, edtInputUsername, edtInputPassword;
    AppCompatButton btnRegister, btnLogin;
    RadioGroup radioGroup;
    AppCompatRadioButton radioButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtInputName = findViewById(R.id.name);
        edtInputPhone = findViewById(R.id.phone);
        edtInputAlamat = findViewById(R.id.alamat);
        edtInputUsername = findViewById(R.id.username);
        edtInputPassword = findViewById(R.id.password);

        btnRegister = findViewById(R.id.sign_up_button);
        btnLogin = findViewById(R.id.sign_in_button);

        radioGroup = findViewById(R.id.Rgrup);

        final LoadingDialog loadingDialog = new LoadingDialog(RegisterActivity.this);

        btnLogin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        //Menyimpan data ke firebase setelah click
        btnRegister.setOnClickListener(v -> {
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference("Users");

            int radioId = radioGroup.getCheckedRadioButtonId();

            radioButton = findViewById(radioId);

            //Mendapatkan semua Nilai
            String name = edtInputName.getText().toString();
            String phone = edtInputPhone.getText().toString();
            String alamat = edtInputAlamat.getText().toString();
            String status = radioButton.getText().toString();
            String username = edtInputUsername.getText().toString();
            String password = edtInputPassword.getText().toString();

            if (name.isEmpty()) {
                edtInputName.setError(getString(R.string.eror_name));
                edtInputName.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                edtInputPhone.setError(getString(R.string.eror_phone));
                edtInputPhone.requestFocus();
                return;
            }
            if (phone.length() < 10) {
                edtInputPhone.setError(getString(R.string.eror_phone2));
                edtInputPhone.requestFocus();
                return;
            }
            if (alamat.isEmpty()) {
                edtInputAlamat.setError(getString(R.string.alamat_eror));
                edtInputAlamat.requestFocus();
                return;
            }
            if (username.isEmpty()) {
                edtInputUsername.setError(getString(R.string.eror_username));
                edtInputUsername.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                edtInputPassword.setError(getString(R.string.hint_password));
                edtInputPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                edtInputPassword.setError(getString(R.string.minimum_password));
                edtInputPassword.requestFocus();
                return;
            }

            UserHelperClass helperClass = new UserHelperClass(name, phone, alamat, status, username, password);

            loadingDialog.startLoadingDialog();
            reference.child(username).setValue(helperClass).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    loadingDialog.dismissDialog();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            });
        });
    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Status Kunjungan Anda: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

}