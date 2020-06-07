package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OUTPUT {

    @SerializedName("DATA")
    @Expose
    private DATA dATA;
    @SerializedName("NAVIGATION")
    @Expose
    private NAVIGATION nAVIGATION;

    public DATA getDATA() {
        return dATA;
    }

    public void setDATA(DATA dATA) {
        this.dATA = dATA;
    }

    public NAVIGATION getNAVIGATION() {
        return nAVIGATION;
    }

    public void setNAVIGATION(NAVIGATION nAVIGATION) {
        this.nAVIGATION = nAVIGATION;
    }

}
