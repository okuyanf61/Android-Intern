package com.mehmetfatih.moviesapp;

public class ApiUtils {

    public static final String BASE_URL = "https://mfoandroid.000webhostapp.com/";

    public static FilmlerDaoInterface getFilmlerDaoInterface() {
        return RetrofitClient.getClient(BASE_URL).create(FilmlerDaoInterface.class);
    }

    public static KategorilerDaoInterface getKategorilerDaoInterface() {
        return RetrofitClient.getClient(BASE_URL).create(KategorilerDaoInterface.class);
    }
}


