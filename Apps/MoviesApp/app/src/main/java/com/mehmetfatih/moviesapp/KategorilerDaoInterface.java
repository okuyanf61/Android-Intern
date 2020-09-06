package com.mehmetfatih.moviesapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KategorilerDaoInterface {

    @GET("filmler/tum_kategoriler.php")
    Call<KategoriCevap> tumKategoriler();
}
