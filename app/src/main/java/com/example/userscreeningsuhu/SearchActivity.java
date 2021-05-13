package com.example.userscreeningsuhu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    DatabaseReference tname;
    private AutoCompleteTextView tsearch;
    private ListView  listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tname = FirebaseDatabase.getInstance().getReference("name");

        listView = findViewById(R.id.myList);
        tsearch = findViewById(R.id.searchView);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        tname.addListenerForSingleValueEvent(eventListener);
        }
        private void dataSearch(DataSnapshot snapshot) {
            Log.d("name", "dataSearch: ");
            ArrayList<String> names = new ArrayList<>();
            if (snapshot.exists()) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String Nama = dataSnapshot.child("name").getValue(String.class);
                    names.add(Nama);

                }
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
                tsearch.setAdapter(adapter);
                tsearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });
            } else {
                Log.d("name", "Tidak ditemukan");
            }
        }

        public void back (View view){
        finish();}}


