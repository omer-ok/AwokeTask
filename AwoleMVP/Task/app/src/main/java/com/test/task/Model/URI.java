package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class URI {
    @SerializedName("SOURCE")
    @Expose
    private String sOURCE;
    @SerializedName("PARSED")
    @Expose
    private String pARSED;

    public String getSOURCE() {
        return sOURCE;
    }

    public void setSOURCE(String sOURCE) {
        this.sOURCE = sOURCE;
    }

    public String getPARSED() {
        return pARSED;
    }

    public void setPARSED(String pARSED) {
        this.pARSED = pARSED;
    }
}
