package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DATA {

    @SerializedName("ITEMS")
    @Expose
    private List<ITEM> iTEMS = null;
    @SerializedName("TITLE")
    @Expose
    private String tITLE;

    public List<ITEM> getITEMS() {
        return iTEMS;
    }

    public void setITEMS(List<ITEM> iTEMS) {
        this.iTEMS = iTEMS;
    }

    public String getTITLE() {
        return tITLE;
    }

    public void setTITLE(String tITLE) {
        this.tITLE = tITLE;
    }

}
