package com.example.userscreeningsuhu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userscreeningsuhu.callbacks.OnButtonClickListener;
import com.example.userscreeningsuhu.R;
import com.example.userscreeningsuhu.model.DataButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterTombol extends RecyclerView.Adapter<AdapterTombol.TombolViewHolder> {
    ArrayList<DataButton> dataButtons;
    OnButtonClickListener listener;
    Context context;

    public AdapterTombol(Context context, ArrayList<DataButton> dataButtons, OnButtonClickListener listener) {
        this.dataButtons = dataButtons;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTombol.TombolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lokasi, parent, false);
        return new TombolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TombolViewHolder holder, int position) {
        AppCompatButton button = holder.itemView.findViewById(R.id.screening_button);
        button.setText(dataButtons.get(position).getId());
        button.setOnClickListener((view -> listener.onButtonClick(dataButtons.get(position))));
    }

    @Override
    public int getItemCount() {
        return dataButtons.size();
    }

    static class TombolViewHolder extends RecyclerView.ViewHolder {
        public TombolViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}