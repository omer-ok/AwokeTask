package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NAVIGATION {


    @SerializedName("TOTAL")
    @Expose
    private Integer tOTAL;
    @SerializedName("COUNT")
    @Expose
    private Integer cOUNT;
    @SerializedName("PAGE")
    @Expose
    private Integer pAGE;
    @SerializedName("MAX_PAGES")
    @Expose
    private Integer mAXPAGES;

    public Integer getTOTAL() {
        return tOTAL;
    }

    public void setTOTAL(Integer tOTAL) {
        this.tOTAL = tOTAL;
    }

    public Integer getCOUNT() {
        return cOUNT;
    }

    public void setCOUNT(Integer cOUNT) {
        this.cOUNT = cOUNT;
    }

    public Integer getPAGE() {
        return pAGE;
    }

    public void setPAGE(Integer pAGE) {
        this.pAGE = pAGE;
    }

    public Integer getMAXPAGES() {
        return mAXPAGES;
    }

    public void setMAXPAGES(Integer mAXPAGES) {
        this.mAXPAGES = mAXPAGES;
    }


}
