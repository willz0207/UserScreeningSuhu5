package com.example.userscreeningsuhu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RiwayatAdmin extends AppCompatActivity {

    RecyclerView recView;
    adapterAdmin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_admin);

        final LoadingDialog loadingDialog = new LoadingDialog(RiwayatAdmin.this);

        Toolbar mMyToolbar = (Toolbar) findViewById(R.id.toolbar);

        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        if (getFragmentManager().getBackStackEntryCount() > 0) {
                            getFragmentManager().popBackStack();
                            return;
                        }
                        RiwayatAdmin.super.onBackPressed();
                    }
                }, 3000);

            }
        });

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModelRiwayat> options =
                new FirebaseRecyclerOptions.Builder<ModelRiwayat>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Hasil"), ModelRiwayat.class)
                    .build();

        admin= new adapterAdmin(options);
        recView.setAdapter(admin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        admin.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        admin.stopListening();
    }


}