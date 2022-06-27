package com.patriciameta.ajr_customer.models;

import com.google.gson.annotations.SerializedName;

public class Aset {
    private Long id;
    private String nama_mobil,tipe_mobil,jenis_transmisi,jenis_bahan_bakar,warna_mobil;
    private Float volume_bahan_bakar;
    private Integer kapasitas_mobil;
    private String fasilitas_mobil,plat_nomor,no_stnk,kategori_aset,tanggal_service_akhir,status_tersedia;
    private Float biaya_sewa;
    private String pemilik_id,periode_mulai_kontrak,periode_selesai_kontrak,status_kontrak,img;

    public Aset(Long id,String nama_mobil, String tipe_mobil, String jenis_transmisi, String jenis_bahan_bakar, String warna_mobil, Float volume_bahan_bakar,
                Integer kapasitas_mobil, String fasilitas_mobil, String plat_nomor, String no_stnk, String kategori_aset, String tanggal_service_akhir,
                String status_tersedia, Float biaya_sewa, String pemilik_id, String periode_mulai_kontrak, String periode_selesai_kontrak, String status_kontrak, String img) {
        this.id = id;
        this.nama_mobil = nama_mobil;
        this.tipe_mobil = tipe_mobil;
        this.jenis_transmisi = jenis_transmisi;
        this.jenis_bahan_bakar = jenis_bahan_bakar;
        this.warna_mobil = warna_mobil;
        this.volume_bahan_bakar = volume_bahan_bakar;
        this.kapasitas_mobil = kapasitas_mobil;
        this.fasilitas_mobil = fasilitas_mobil;
        this.plat_nomor = plat_nomor;
        this.no_stnk = no_stnk;
        this.kategori_aset = kategori_aset;
        this.tanggal_service_akhir = tanggal_service_akhir;
        this.status_tersedia = status_tersedia;
        this.biaya_sewa = biaya_sewa;
        this.pemilik_id = pemilik_id;
        this.periode_mulai_kontrak = periode_mulai_kontrak;
        this.periode_selesai_kontrak = periode_selesai_kontrak;
        this.status_kontrak = status_kontrak;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getTipe_mobil() {
        return tipe_mobil;
    }

    public void setTipe_mobil(String tipe_mobil) {
        this.tipe_mobil = tipe_mobil;
    }

    public String getJenis_transmisi() {
        return jenis_transmisi;
    }

    public void setJenis_transmisi(String jenis_transmisi) {
        this.jenis_transmisi = jenis_transmisi;
    }

    public String getJenis_bahan_bakar() {
        return jenis_bahan_bakar;
    }

    public void setJenis_bahan_bakar(String jenis_bahan_bakar) {
        this.jenis_bahan_bakar = jenis_bahan_bakar;
    }

    public String getWarna_mobil() {
        return warna_mobil;
    }

    public void setWarna_mobil(String warna_mobil) {
        this.warna_mobil = warna_mobil;
    }

    public Float getVolume_bahan_bakar() {
        return volume_bahan_bakar;
    }

    public void setVolume_bahan_bakar(Float volume_bahan_bakar) {
        this.volume_bahan_bakar = volume_bahan_bakar;
    }

    public Integer getKapasitas_mobil() {
        return kapasitas_mobil;
    }

    public void setKapasitas_mobil(Integer kapasitas_mobil) {
        this.kapasitas_mobil = kapasitas_mobil;
    }

    public String getFasilitas_mobil() {
        return fasilitas_mobil;
    }

    public void setFasilitas_mobil(String fasilitas_mobil) {
        this.fasilitas_mobil = fasilitas_mobil;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getNo_stnk() {
        return no_stnk;
    }

    public void setNo_stnk(String no_stnk) {
        this.no_stnk = no_stnk;
    }

    public String getKategori_aset() {
        return kategori_aset;
    }

    public void setKategori_aset(String kategori_aset) {
        this.kategori_aset = kategori_aset;
    }

    public String getTanggal_service_akhir() {
        return tanggal_service_akhir;
    }

    public void setTanggal_service_akhir(String tanggal_service_akhir) {
        this.tanggal_service_akhir = tanggal_service_akhir;
    }

    public String getStatus_tersedia() {
        return status_tersedia;
    }

    public void setStatus_tersedia(String status_tersedia) {
        this.status_tersedia = status_tersedia;
    }

    public Float getBiaya_sewa() {
        return biaya_sewa;
    }

    public void setBiaya_sewa(Float biaya_sewa) {
        this.biaya_sewa = biaya_sewa;
    }

    public String getPemilik_id() {
        return pemilik_id;
    }

    public void setPemilik_id(String pemilik_id) {
        this.pemilik_id = pemilik_id;
    }

    public String getPeriode_mulai_kontrak() {
        return periode_mulai_kontrak;
    }

    public void setPeriode_mulai_kontrak(String periode_mulai_kontrak) {
        this.periode_mulai_kontrak = periode_mulai_kontrak;
    }

    public String getPeriode_selesai_kontrak() {
        return periode_selesai_kontrak;
    }

    public void setPeriode_selesai_kontrak(String periode_selesai_kontrak) {
        this.periode_selesai_kontrak = periode_selesai_kontrak;
    }

    public String getStatus_kontrak() {
        return status_kontrak;
    }

    public void setStatus_kontrak(String status_kontrak) {
        this.status_kontrak = status_kontrak;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
