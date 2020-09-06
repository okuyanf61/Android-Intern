
package com.mehmetfatih.moviesapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filmler implements Serializable {

    @SerializedName("film_id")
    @Expose
    private String filmId;
    @SerializedName("film_ad")
    @Expose
    private String filmAd;
    @SerializedName("film_yil")
    @Expose
    private String filmYil;
    @SerializedName("film_resim")
    @Expose
    private String filmResim;
    @SerializedName("kategori")
    @Expose
    private Kategoriler kategori;
    @SerializedName("yonetmen")
    @Expose
    private Yonetmen yonetmen;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmAd() {
        return filmAd;
    }

    public void setFilmAd(String filmAd) {
        this.filmAd = filmAd;
    }

    public String getFilmYil() {
        return filmYil;
    }

    public void setFilmYil(String filmYil) {
        this.filmYil = filmYil;
    }

    public String getFilmResim() {
        return filmResim;
    }

    public void setFilmResim(String filmResim) {
        this.filmResim = filmResim;
    }

    public Kategoriler getKategori() {
        return kategori;
    }

    public void setKategori(Kategoriler kategori) {
        this.kategori = kategori;
    }

    public Yonetmen getYonetmen() {
        return yonetmen;
    }

    public void setYonetmen(Yonetmen yonetmen) {
        this.yonetmen = yonetmen;
    }

}
