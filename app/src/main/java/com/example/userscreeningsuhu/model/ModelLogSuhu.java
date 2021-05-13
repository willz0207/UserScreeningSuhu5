package com.example.userscreeningsuhu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLogSuhu {

    @SerializedName("Suhu")
    @Expose
    private String suhu;
    @SerializedName("Lokasi")
    @Expose
    private String lokasi;
    @SerializedName("Hari")
    @Expose
    private String hari;
    @SerializedName("Jam")
    @Expose
    private String jam;
    @SerializedName("Tanggal")
    @Expose
    private String tanggal;

    public ModelLogSuhu() {
    }

    public ModelLogSuhu(String suhu, String lokasi, String hari, String jam, String tanggal) {
        this.suhu = suhu;
        this.lokasi = lokasi;
        this.hari = hari;
        this.jam = jam;
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String Suhu) {
        this.suhu = suhu;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
