package com.example.userscreeningsuhu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userscreeningsuhu.R;
import com.example.userscreeningsuhu.model.ModelRiwayat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterAdmin extends RecyclerView.Adapter <AdapterAdmin.MyViewholder> implements Filterable {

    Context context;
    ArrayList<ModelRiwayat> modelRiwayat;
    private ArrayList<ModelRiwayat> dataFilterRiwayat;

    public AdapterAdmin(Context context, ArrayList<ModelRiwayat> modelRiwayat) {
        this.context = context;
        this.modelRiwayat = modelRiwayat;
        this.dataFilterRiwayat = modelRiwayat;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlerow,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.tvName.setText(dataFilterRiwayat.get(position).getName());
        holder.tvPhone.setText(dataFilterRiwayat.get(position).getPhone());
        holder.tvStatus.setText(dataFilterRiwayat.get(position).getStatus());
        holder.tvSuhu.setText(dataFilterRiwayat.get(position).getSuhu());
        holder.tvWaktu.setText(dataFilterRiwayat.get(position).getSwaktu());
        holder.tvAlamat.setText(dataFilterRiwayat.get(position).getAlamat());
        holder.tvLokasi.setText(dataFilterRiwayat.get(position).getLokasi());

        holder.imgDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tvName.getContext());
            builder.setTitle("Hapus Riwayat Screening Suhu");
            builder.setMessage("Hapus Riwayat Ini?");

            builder.setPositiveButton("Iya", (dialog, which) -> {});
            builder.setNegativeButton("Batal", (dialog, which) -> {

            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return dataFilterRiwayat.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();
                if (str.isEmpty()) {
                    dataFilterRiwayat = modelRiwayat;
                } else {
                    ArrayList<ModelRiwayat> result = new ArrayList<>();
                    for (ModelRiwayat data : modelRiwayat){
                        if (data.getName().toLowerCase(Locale.getDefault()).contains(str.toLowerCase(Locale.getDefault()))) {
                            result.add(data);
                        }
                    }

                    dataFilterRiwayat = result;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataFilterRiwayat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFilterRiwayat = (ArrayList<ModelRiwayat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class MyViewholder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvStatus, tvSuhu, tvWaktu, tvAlamat, tvLokasi;
        ImageView imgDelete;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.namaUser);
            tvPhone = itemView.findViewById(R.id.phoneUser);
            tvStatus = itemView.findViewById(R.id.statusUser);
            tvSuhu = itemView.findViewById(R.id.suhuUser);
            tvAlamat = itemView.findViewById(R.id.alamatUser);
            tvLokasi = itemView.findViewById(R.id.lokasiUser);
            tvWaktu = itemView.findViewById(R.id.timeUser);
            imgDelete = itemView.findViewById(R.id.deleteRiwayat);
        }
    }
}
