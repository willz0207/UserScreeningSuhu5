package com.example.userscreeningsuhu.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.userscreeningsuhu.R;

public class LoadingDialog {
    private final Context context;
    private AlertDialog dialog;

    public LoadingDialog(Context context){
        this.context = context;
    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}