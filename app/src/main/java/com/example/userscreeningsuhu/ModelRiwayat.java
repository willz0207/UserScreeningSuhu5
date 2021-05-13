package com.example.userscreeningsuhu;

public class ModelRiwayat {
    String name, phone, status, alamat, lokasi, suhu, swaktu, delete;

    ModelRiwayat() {

    }

    public ModelRiwayat(String name, String phone, String status, String alamat, String lokasi, String suhu, String swaktu, String delete) {
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.alamat = alamat;
        this.lokasi = lokasi;
        this.suhu = suhu;
        this.swaktu = swaktu;
        this.delete = delete;
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

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
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

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getSwaktu() {
        return swaktu;
    }

    public void setSwaktu(String swaktu) {
        this.swaktu = swaktu;
    }
}
