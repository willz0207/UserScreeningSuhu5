package com.example.userscreeningsuhu.helper;

public class SuhuHelperClass {

    String name, phone, status, alamat, suhu, lokasi, swaktu;

    public SuhuHelperClass() {

    }

    public SuhuHelperClass(String name, String phone, String status, String alamat, String suhu, String lokasi, String swaktu) {
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.alamat = alamat;
        this.suhu = suhu;
        this.lokasi = lokasi;
        this.swaktu = swaktu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwaktu() {
        return swaktu;
    }

    public void setSwaktu(String swaktu) {
        this.swaktu = swaktu;
    }
}
