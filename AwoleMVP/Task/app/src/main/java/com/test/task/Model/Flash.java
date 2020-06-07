package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flash {

    @SerializedName("STATUS")
    @Expose
    private STATUS sTATUS;
    @SerializedName("OUTPUT")
    @Expose
    private OUTPUT oUTPUT;
    @SerializedName("API")
    @Expose
    private API aPI;

    public STATUS getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(STATUS sTATUS) {
        this.sTATUS = sTATUS;
    }

    public OUTPUT getOUTPUT() {
        return oUTPUT;
    }

    public void setOUTPUT(OUTPUT oUTPUT) {
        this.oUTPUT = oUTPUT;
    }

    public API getAPI() {
        return aPI;
    }

    public void setAPI(API aPI) {
        this.aPI = aPI;
    }
}
