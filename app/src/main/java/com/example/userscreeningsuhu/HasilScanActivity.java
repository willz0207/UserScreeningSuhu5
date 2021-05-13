package com.example.userscreeningsuhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.helper.SuhuHelperClass;
import com.example.userscreeningsuhu.model.DataButton;
import com.example.userscreeningsuhu.model.ModelLogSuhu;
import com.example.userscreeningsuhu.utils.WaktuTanggalJam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HasilScanActivity extends AppCompatActivity {

    TextView tvFullName, tvPhone, tvStatus, tvAlamat, tvHasilSuhu, tvHasilLokasi, tvWaktu;
    AppCompatButton btnSubmit;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();
    DataButton dataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan);

        tvFullName = findViewById(R.id.namaUser);
        tvPhone = findViewById(R.id.phoneUser);
        tvStatus = findViewById(R.id.statusUser);
        tvHasilSuhu = findViewById(R.id.readSuhu);
        btnSubmit = findViewById(R.id.submit);
        tvHasilLokasi = findViewById(R.id.readLokasi);
        tvWaktu = findViewById(R.id.waktu);
        tvAlamat = findViewById(R.id.alamatUser);
        dataButton = new Gson().fromJson(getIntent().getStringExtra("data"), DataButton.class);

        final LoadingDialog loadingDialog = new LoadingDialog(HasilScanActivity.this);

        showAllUserData();

        Query querySuhu = reference.child("Riwayat").orderByKey().limitToLast(1);
        querySuhu.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot ds: snapshot.getChildren()) {
                   String data = new Gson().toJson(ds.getValue());
                   ModelLogSuhu model = new Gson().fromJson(data, ModelLogSuhu.class);
                   String Suhu = model.getSuhu();
                   tvHasilSuhu.setText(Suhu);
                   String lokasi = model.getLokasi();
                   tvHasilLokasi.setText(lokasi);
                   String hari = new WaktuTanggalJam().hanyaTanggal(model.getTanggal());
                   tvWaktu.setText(model.getHari() + ", " +hari +"\nJam "+model.getJam());
               }

               reference.child("status").child(dataButton.getId()).setValue(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSubmit.setOnClickListener(v -> {
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference("Hasil");

            String name = tvFullName.getText().toString();
            String number = tvPhone.getText().toString();
            String stat = tvStatus.getText().toString();
            String suhu = tvHasilSuhu.getText().toString();
            String swaktu = tvWaktu.getText().toString();
            String lokasi = tvHasilLokasi.getText().toString();
            String alamatS = tvAlamat.getText().toString();

            SuhuHelperClass suhuHelperClass = new SuhuHelperClass(name, number,  stat, alamatS, suhu, lokasi, swaktu);

            loadingDialog.startLoadingDialog();
            reference.child(name).setValue(suhuHelperClass).addOnCompleteListener(this, task -> {
                loadingDialog.dismissDialog();
                startActivity(new Intent(HasilScanActivity.this, MainActivity.class));
            });
        });


    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_fullName = intent.getStringExtra("name");
        String user_phone = intent.getStringExtra("phone");
        String user_status = intent.getStringExtra("status");
        String user_alamat = intent.getStringExtra("alamat");

        tvFullName.setText(user_fullName);
        tvPhone.setText(user_phone);
        tvStatus.setText(user_status);
        tvAlamat.setText(user_alamat);
    }
}