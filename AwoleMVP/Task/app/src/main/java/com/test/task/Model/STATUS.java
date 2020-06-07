package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class STATUS {

    @SerializedName("CODE")
    @Expose
    private Integer cODE;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("KEY")
    @Expose
    private String kEY;
    @SerializedName("LCR")
    @Expose
    private Integer lCR;
    @SerializedName("SRV")
    @Expose
    private String sRV;
    @SerializedName("MSRV")
    @Expose
    private String mSRV;

    public Integer getCODE() {
        return cODE;
    }

    public void setCODE(Integer cODE) {
        this.cODE = cODE;
    }

    public String getMESSAGE() {
        return mESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public String getKEY() {
        return kEY;
    }

    public void setKEY(String kEY) {
        this.kEY = kEY;
    }

    public Integer getLCR() {
        return lCR;
    }

    public void setLCR(Integer lCR) {
        this.lCR = lCR;
    }

    public String getSRV() {
        return sRV;
    }

    public void setSRV(String sRV) {
        this.sRV = sRV;
    }

    public String getMSRV() {
        return mSRV;
    }

    public void setMSRV(String mSRV) {
        this.mSRV = mSRV;
    }


}
