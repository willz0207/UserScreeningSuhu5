package com.example.userscreeningsuhu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WaktuTanggalJam {

    public String hanyaTanggal(String tgl) {
        String hasil_tgl = "";

        try {
            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date newDate = formatdate.parse(tgl);
            formatdate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            hasil_tgl = formatdate.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hasil_tgl;
    }
}
