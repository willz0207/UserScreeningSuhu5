package com.example.userscreeningsuhu.helper;

public class UserHelperClass {
    String name, phone, alamat, status, username, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String phone, String alamat, String status, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.alamat = alamat;
        this.status = status;
        this.username = username;
        this.password = password;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
