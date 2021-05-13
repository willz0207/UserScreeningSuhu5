package com.example.userscreeningsuhu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Document;

public class adapterMain extends FirebaseRecyclerAdapter <ModelRiwayat, adapterMain.NoteHolder> {



    public adapterMain(@NonNull FirebaseRecyclerOptions<ModelRiwayat> options) {
        super(options);
    }




    /*@Override
    protected void onBindViewHolder(@NonNull NoteHolder noteHolder, int i, @NonNull MainActivity mainActivity) {

    }*/

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder noteHolder, int i, @NonNull ModelRiwayat mainActivity) {
        noteHolder.txtstatus.setText(mainActivity.getStatus());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new NoteHolder(v);
    }


    class NoteHolder extends RecyclerView.ViewHolder {
        TextView txtstatus;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            txtstatus = itemView.findViewById(R.id.screening);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                }
            });
        }
    }

        public interface onClickInterface{
        void onItemClick(DataSnapshot dataSnapshot, int position);
        }
        public void setOnclickListener(onClickInterface listener){

        }
}

