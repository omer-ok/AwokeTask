package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API {

    @SerializedName("MIN_APP_VER")
    @Expose
    private Integer mINAPPVER;
    @SerializedName("MIN_APP_VERSION")
    @Expose
    private Integer mINAPPVERSION;
    @SerializedName("VERSION")
    @Expose
    private Double vERSION;
    @SerializedName("FORMAT")
    @Expose
    private String fORMAT;
    @SerializedName("LANG")
    @Expose
    private String lANG;
    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("CURRENCY_SYM")
    @Expose
    private String cURRENCYSYM;
    @SerializedName("URI")
    @Expose
    private URI uRI;

    public Integer getMINAPPVER() {
        return mINAPPVER;
    }

    public void setMINAPPVER(Integer mINAPPVER) {
        this.mINAPPVER = mINAPPVER;
    }

    public Integer getMINAPPVERSION() {
        return mINAPPVERSION;
    }

    public void setMINAPPVERSION(Integer mINAPPVERSION) {
        this.mINAPPVERSION = mINAPPVERSION;
    }

    public Double getVERSION() {
        return vERSION;
    }

    public void setVERSION(Double vERSION) {
        this.vERSION = vERSION;
    }

    public String getFORMAT() {
        return fORMAT;
    }

    public void setFORMAT(String fORMAT) {
        this.fORMAT = fORMAT;
    }

    public String getLANG() {
        return lANG;
    }

    public void setLANG(String lANG) {
        this.lANG = lANG;
    }

    public String getCURRENCY() {
        return cURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getCURRENCYSYM() {
        return cURRENCYSYM;
    }

    public void setCURRENCYSYM(String cURRENCYSYM) {
        this.cURRENCYSYM = cURRENCYSYM;
    }

    public URI getURI() {
        return uRI;
    }

    public void setURI(URI uRI) {
        this.uRI = uRI;
    }

}
