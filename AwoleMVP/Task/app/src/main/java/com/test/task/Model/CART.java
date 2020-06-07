package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CART {

    @SerializedName("TITLE")
    @Expose
    private String tITLE;
    @SerializedName("VALUE")
    @Expose
    private String vALUE;

    public String getTITLE() {
        return tITLE;
    }

    public void setTITLE(String tITLE) {
        this.tITLE = tITLE;
    }

    public String getVALUE() {
        return vALUE;
    }

    public void setVALUE(String vALUE) {
        this.vALUE = vALUE;
    }

}
