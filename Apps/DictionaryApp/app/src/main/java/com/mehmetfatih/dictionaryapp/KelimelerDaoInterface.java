package com.mehmetfatih.dictionaryapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KelimelerDaoInterface {

    @GET("sozluk/tum_kelimeler.php")
    Call<KelimelerCevap> tumKelimeler();

    @POST("sozluk/kelime_ara.php")
    @FormUrlEncoded
    Call<KelimelerCevap> kelimeAra(@Field("ingilizce") String ingilizce);
}
