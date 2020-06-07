package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CARTDATA {

    @SerializedName("SKU")
    @Expose
    private String sKU;
    @SerializedName("UID")
    @Expose
    private String uID;
    @SerializedName("SSID")
    @Expose
    private String sSID;

    public String getSKU() {
        return sKU;
    }

    public void setSKU(String sKU) {
        this.sKU = sKU;
    }

    public String getUID() {
        return uID;
    }

    public void setUID(String uID) {
        this.uID = uID;
    }

    public String getSSID() {
        return sSID;
    }

    public void setSSID(String sSID) {
        this.sSID = sSID;
    }


}
