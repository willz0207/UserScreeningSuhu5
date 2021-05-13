package com.example.userscreeningsuhu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class adapterAdmin extends FirebaseRecyclerAdapter <ModelRiwayat, adapterAdmin.myviewholder> {

    public adapterAdmin(@NonNull FirebaseRecyclerOptions<ModelRiwayat> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull ModelRiwayat modelRiwayat) {

        holder.name.setText(modelRiwayat.getName());
        holder.phone.setText(modelRiwayat.getPhone());
        holder.status.setText(modelRiwayat.getStatus());
        holder.suhu.setText(modelRiwayat.getSuhu());
        holder.swaktu.setText(modelRiwayat.getSwaktu());
        holder.alamat.setText(modelRiwayat.getAlamat());
        holder.lokasi.setText(modelRiwayat.getLokasi());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Hapus Riwayat Screening Suhu");
                builder.setMessage("Hapus Riwayat Ini?");

                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Hasil")
                                .child(getRef(i).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, phone, status, suhu, swaktu, alamat, lokasi;
        ImageView delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.namaUser);
            phone = itemView.findViewById(R.id.phoneUser);
            status = itemView.findViewById(R.id.statusUser);
            suhu = itemView.findViewById(R.id.suhuUser);
            alamat = itemView.findViewById(R.id.alamatUser);
            lokasi = itemView.findViewById(R.id.lokasiUser);
            swaktu = itemView.findViewById(R.id.timeUser);
            delete = itemView.findViewById(R.id.deleteRiwayat);
        }
    }
}
