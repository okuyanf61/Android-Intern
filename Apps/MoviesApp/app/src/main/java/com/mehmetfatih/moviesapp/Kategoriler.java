
package com.mehmetfatih.moviesapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kategoriler implements Serializable {

    @SerializedName("kategori_id")
    @Expose
    private String kategoriId;
    @SerializedName("kategori_ad")
    @Expose
    private String kategoriAd;

    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKategoriAd() {
        return kategoriAd;
    }

    public void setKategoriAd(String kategoriAd) {
        this.kategoriAd = kategoriAd;
    }

}
