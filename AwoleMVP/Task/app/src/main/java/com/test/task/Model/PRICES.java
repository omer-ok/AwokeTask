package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PRICES {

    @SerializedName("PRICE_NEW")
    @Expose
    private String pRICENEW;
    @SerializedName("PRICE_OLD")
    @Expose
    private String pRICEOLD;
    @SerializedName("DISCOUNT")
    @Expose
    private String dISCOUNT;
    @SerializedName("PERCENT")
    @Expose
    private String pERCENT;

    public String getPRICENEW() {
        return pRICENEW;
    }

    public void setPRICENEW(String pRICENEW) {
        this.pRICENEW = pRICENEW;
    }

    public String getPRICEOLD() {
        return pRICEOLD;
    }

    public void setPRICEOLD(String pRICEOLD) {
        this.pRICEOLD = pRICEOLD;
    }

    public String getDISCOUNT() {
        return dISCOUNT;
    }

    public void setDISCOUNT(String dISCOUNT) {
        this.dISCOUNT = dISCOUNT;
    }

    public String getPERCENT() {
        return pERCENT;
    }

    public void setPERCENT(String pERCENT) {
        this.pERCENT = pERCENT;
    }


}
