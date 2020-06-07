package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IMAGE {

    @SerializedName("SRC")
    @Expose
    private String sRC;

    public String getSRC() {
        return sRC;
    }

    public void setSRC(String sRC) {
        this.sRC = sRC;
    }

}
