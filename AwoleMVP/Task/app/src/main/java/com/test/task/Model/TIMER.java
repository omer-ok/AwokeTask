package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TIMER {

    @SerializedName("D")
    @Expose
    private String d;
    @SerializedName("H")
    @Expose
    private String h;
    @SerializedName("I")
    @Expose
    private String i;
    @SerializedName("S")
    @Expose
    private String s;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }


}
