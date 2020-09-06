
package com.mehmetfatih.moviesapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Yonetmen implements Serializable {

    @SerializedName("yonetmen_id")
    @Expose
    private String yonetmenId;
    @SerializedName("yonetmen_ad")
    @Expose
    private String yonetmenAd;

    public String getYonetmenId() {
        return yonetmenId;
    }

    public void setYonetmenId(String yonetmenId) {
        this.yonetmenId = yonetmenId;
    }

    public String getYonetmenAd() {
        return yonetmenAd;
    }

    public void setYonetmenAd(String yonetmenAd) {
        this.yonetmenAd = yonetmenAd;
    }

}
