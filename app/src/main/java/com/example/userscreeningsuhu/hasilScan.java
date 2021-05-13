package com.example.userscreeningsuhu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.userscreeningsuhu.helper.SuhuHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class hasilScan extends AppCompatActivity {

    TextView fullname, phone, status, alamat, hasilSuhu, hasilLokasi, waktu;
    AppCompatButton bSubmit;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan);

        fullname = findViewById(R.id.namaUser);
        phone = findViewById(R.id.phoneUser);
        status = findViewById(R.id.statusUser);
        hasilSuhu = findViewById(R.id.readSuhu);
        bSubmit = findViewById(R.id.submit);
        hasilLokasi = findViewById(R.id.readLokasi);
        waktu = findViewById(R.id.waktu);
        alamat = findViewById(R.id.alamatUser);

        final LoadingDialog loadingDialog = new LoadingDialog(hasilScan.this);

        showAllUserData();

        Query qSuhu = reference.child("Riwayat").orderByKey().limitToLast(1);
       // rf.keepSynced(true);
        qSuhu.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot ds: snapshot.getChildren()) {
                   String data = new Gson().toJson(ds.getValue());
                   ModelLogSuhu model = new Gson().fromJson(data, ModelLogSuhu.class);
                   String Suhu = model.getSuhu();
                   hasilSuhu.setText(Suhu);
                   String lokasi = model.getLokasi();
                   hasilLokasi.setText(lokasi);
                   String hari = new WaktuTanggalJam().HanyaTanggal(model.getTanggal());
                   waktu.setText(model.getHari() + ", " +hari +"\nJam "+model.getJam());
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Hasil");

                String name = fullname.getText().toString();
                String number = phone.getText().toString();
                String stat = status.getText().toString();
                String suhu = hasilSuhu.getText().toString();
                String swaktu = waktu.getText().toString();
                String lokasi = hasilLokasi.getText().toString();
                String alamatS = alamat.getText().toString();

                SuhuHelperClass suhuHelperClass = new SuhuHelperClass(name, number,  stat, alamatS, suhu, lokasi, swaktu);
                reference.child(name).setValue(suhuHelperClass);

                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(hasilScan.this, MainActivity.class));
                    }
                }, 3000);

            }
        });


    }


    private void AmbilData() {


    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_fullName = intent.getStringExtra("name");
        String user_phone = intent.getStringExtra("phone");
        String user_status = intent.getStringExtra("status");
        String user_alamat = intent.getStringExtra("alamat");

        fullname.setText(user_fullName);
        phone.setText(user_phone);
        status.setText(user_status);
        alamat.setText(user_alamat);
    }


}