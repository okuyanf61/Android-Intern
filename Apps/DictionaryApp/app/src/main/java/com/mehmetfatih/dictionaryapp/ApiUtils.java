package com.mehmetfatih.dictionaryapp;

public class ApiUtils {

    public static final String BASE_URL = "https://mfoandroid.000webhostapp.com/";

    public static KelimelerDaoInterface getKisilerDaoInterfeace() {
        return RetrofitClient.getClient(BASE_URL).create(KelimelerDaoInterface.class);
    }
}


