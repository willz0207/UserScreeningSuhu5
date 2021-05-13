package com.example.userscreeningsuhu.history;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.userscreeningsuhu.R;
import com.example.userscreeningsuhu.adapter.AdapterAdmin;
import com.example.userscreeningsuhu.dialog.LoadingDialog;
import com.example.userscreeningsuhu.model.ModelRiwayat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiwayatAdminActivity extends AppCompatActivity {

    RecyclerView recView;
    EditText edtFilter;
    AdapterAdmin adminAdapter;
    private final ArrayList<ModelRiwayat> dataRiwayat = new ArrayList<>();
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_admin);

        Toolbar mMyToolbar = findViewById(R.id.toolbar);

        loadingDialog = new LoadingDialog(RiwayatAdminActivity.this);
        adminAdapter = new AdapterAdmin(this, dataRiwayat);

        recView = findViewById(R.id.recView);
        edtFilter = findViewById(R.id.edt_filter);

        mMyToolbar.setNavigationOnClickListener(v -> onBackPressed());
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adminAdapter);

        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                adminAdapter.getFilter().filter(editable.toString());
            }
        });

        getDataStatus();
    }

    private void getDataStatus() {
        DatabaseReference dataReference = rootRef.child("Hasil");
        loadingDialog.startLoadingDialog();
        dataReference.get().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null) {
                    dataRiwayat.clear();
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        dataRiwayat.add(ds.getValue(ModelRiwayat.class));
                    }

                    adminAdapter.notifyDataSetChanged();
                    loadingDialog.dismissDialog();
                }
            }
        });
    }
}