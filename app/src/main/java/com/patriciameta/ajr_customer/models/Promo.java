package com.patriciameta.ajr_customer.models;

public class Promo {
    private Long id;
    private String kode_promo;
    private String jenis_promo;
    private String keterangan;
    private Float diskon_promo;
    private String status_promo;

    public Promo(Long id, String kode_promo, String jenis_promo, String keterangan, Float diskon_promo, String status_promo) {
        this.id = id;
        this.kode_promo = kode_promo;
        this.jenis_promo = jenis_promo;
        this.keterangan = keterangan;
        this.diskon_promo = diskon_promo;
        this.status_promo = status_promo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode_promo() {
        return kode_promo;
    }

    public void setKode_promo(String kode_promo) {
        this.kode_promo = kode_promo;
    }

    public String getJenis_promo() {
        return jenis_promo;
    }

    public void setJenis_promo(String jenis_promo) {
        this.jenis_promo = jenis_promo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Float getDiskon_promo() {
        return diskon_promo;
    }

    public void setDiskon_promo(Float diskon_promo) {
        this.diskon_promo = diskon_promo;
    }

    public String getStatus_promo() {
        return status_promo;
    }

    public void setStatus_promo(String status_promo) {
        this.status_promo = status_promo;
    }
}
