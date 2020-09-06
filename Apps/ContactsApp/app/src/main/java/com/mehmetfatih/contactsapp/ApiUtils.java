package com.mehmetfatih.contactsapp;


public class ApiUtils {

    public static final String BASE_URL = "https://mfoandroid.000webhostapp.com/";

    public static KisilerdaoInterface getKisilerDaoInterfeace() {
        return RetrofitClient.getClient(BASE_URL).create(KisilerdaoInterface.class);
    }
}


