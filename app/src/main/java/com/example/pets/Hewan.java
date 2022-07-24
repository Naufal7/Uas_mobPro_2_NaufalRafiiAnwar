package com.example.pets;

import java.io.Serializable;

public class Hewan implements Serializable {
    private String key;
    private String nama;
    private String jenis;
    private String keterangan;
    private String tanggal;
    private String gambar;

    public Hewan(){

    }

    public Hewan(String key, String nama, String jenis, String keterangan, String tanggal, String gambar){
        this.key = key;
        this.nama = nama;
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.gambar = gambar;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


}
