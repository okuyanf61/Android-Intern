package com.mehmetfatih.gradeapp;


public class ApiUtils {

    public static final String BASE_URL = "https://mfoandroid.000webhostapp.com/";

    public static NotlarInterface getNotlarDaoInterfeace() {
        return RetrofitClient.getClient(BASE_URL).create(NotlarInterface.class);
    }
}


