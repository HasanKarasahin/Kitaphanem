package com.hk_book_store;

import java.io.Serializable;

/**
 * Created by hasan on 25.12.2017.
 */

public class Kullanici implements Serializable {
    private String kullaniciKey;
    private String kullaniciAdi;
    private String il;

    public Kullanici() {
    }

    public Kullanici(String kullaniciAdi, String kullaniciKey, String il) {
        this.kullaniciAdi = kullaniciAdi;
        this.kullaniciKey = kullaniciKey;
        this.il = il;
    }


    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciKey() {
        return kullaniciKey;
    }

    public void setKullaniciKey(String kullaniciKey) {
        this.kullaniciKey = kullaniciKey;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }
}
