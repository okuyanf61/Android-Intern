package com.mehmetfatih.gradeapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NotlarInterface {

    @GET("notlar/tum_notlar.php")
    Call<NotlarCevap> tumNotlar();

    @POST("notlar/delete_not.php")
    @FormUrlEncoded
    Call<CRUDCevap> notSil(@Field("not_id") int not_id);

    @POST("notlar/insert_not.php")
    @FormUrlEncoded
    Call<CRUDCevap> notEkle(@Field("ders_adi") String ders_adi
            , @Field("not1") int not1
            , @Field("not2") int not2);

    @POST("notlar/update_not.php")
    @FormUrlEncoded
    Call<CRUDCevap> notGuncelle(@Field("not_id") int not_id
            , @Field("ders_adi") String ders_adi
            , @Field("not1") int not1
            , @Field("not2") int not2);


}
