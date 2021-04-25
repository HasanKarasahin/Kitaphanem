package com.hk_book_store;

public class Kitap {
    private String kitapAdi,yazarAdi,paylasanKisi, paylasimTarihi,paylasilanYer;
    private int tip;
    public Kitap(){}

    public Kitap(int tip,String kitapAdi,String yazarAdi,String paylasankisi,String paylasimTarihi,String paylasilanyer){
        this.tip=tip;
        this.setKitapAdi( kitapAdi );
        this.setYazarAdi( yazarAdi );
        this.setPaylasanKisi( paylasankisi );
        this.setPaylasimTarihi( paylasimTarihi );
        this.setPaylasilanYer( paylasilanyer );
    }
    public int getTip(){return tip;}
    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getPaylasanKisi() {
        return paylasanKisi;
    }

    public void setPaylasanKisi(String paylasanKisi) {
        this.paylasanKisi = paylasanKisi;
    }

    public String getPaylasimTarihi() {
        return paylasimTarihi;
    }

    public void setPaylasimTarihi(String paylasimTarihi) {
        this.paylasimTarihi = paylasimTarihi;
    }

    public String getPaylasilanYer() {
        return paylasilanYer;
    }

    public void setPaylasilanYer(String paylasilanYer) {
        this.paylasilanYer = paylasilanYer;
    }}