package com.example.userscreeningsuhu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userscreeningsuhu.adapter.AdapterTombol;
import com.example.userscreeningsuhu.callbacks.OnButtonClickListener;
import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.login.LoginActivity;
import com.example.userscreeningsuhu.model.DataButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnButtonClickListener {

    TextView tvFullName, tvStatus, tvPhone, tvUsername, tvAlamat;
    AppCompatButton btnKeluar;
    RecyclerView rvButton;
    ArrayList<DataButton> arrayButton = new ArrayList<>();
    LoadingDialog loadingDialog;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    AdapterTombol tombolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tombolAdapter = new AdapterTombol(this, arrayButton, this);
        loadingDialog = new LoadingDialog(this);

        tvFullName = findViewById(R.id.nama);
        tvStatus = findViewById(R.id.stat);
        tvPhone = findViewById(R.id.phoneNo);
        tvUsername = findViewById(R.id.username);
        tvAlamat = findViewById(R.id.alamat);
        btnKeluar = findViewById(R.id.keluar);
        rvButton = findViewById(R.id.rv_screening);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvButton.setLayoutManager(layoutManager);
        rvButton.setAdapter(tombolAdapter);

        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
        showAllUserData();

        getDataStatus();

        btnKeluar.setOnClickListener(v -> {
            loadingDialog.startLoadingDialog();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("mulai").setValue(0).addOnSuccessListener(aVoid -> {
                loadingDialog.dismissDialog();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            });
        });
    }

    private void getDataStatus() {
        DatabaseReference dataReference = rootRef.child("status");
        loadingDialog.startLoadingDialog();
        dataReference.get().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null) {
                    arrayButton.clear();
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        String key = ds.getKey();
                        int value = Integer.parseInt(ds.getValue().toString());
                        DataButton data = new DataButton();
                        data.setAksi(value);
                        data.setId(key);
                        arrayButton.add(data);
                    }

                    tombolAdapter.notifyDataSetChanged();
                    loadingDialog.dismissDialog();
                }
            }
        });
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_status = intent.getStringExtra("status");
        String user_phone = intent.getStringExtra("phone");
        String user_username = intent.getStringExtra("username");
        String user_alamat = intent.getStringExtra("alamat");

        tvFullName.setText(user_name);
        tvStatus.setText(user_status);
        tvPhone.setText(user_phone);
        tvUsername.setText(user_username);
        tvAlamat.setText(user_alamat);
    }

    @Override
    public void onButtonClick(DataButton dataButton) {
        loadingDialog.startLoadingDialog();
        rootRef.child("status").child(dataButton.getId()).setValue(1).addOnCompleteListener(this, task -> {
            loadingDialog.dismissDialog();
            Intent intent = new Intent(this, HasilScanActivity.class);
            intent.putExtra("data", new Gson().toJson(dataButton))
                    .putExtra("name", tvFullName.getText().toString())
                    .putExtra("status", tvStatus.getText().toString())
                    .putExtra("phone", tvPhone.getText().toString())
                    .putExtra("alamat", tvAlamat.getText().toString());
            startActivity(intent);
        });
    }
}