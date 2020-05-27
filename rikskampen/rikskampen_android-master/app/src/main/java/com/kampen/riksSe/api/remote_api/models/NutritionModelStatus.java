package com.kampen.riksSe.api.remote_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionModelStatus {


    @SerializedName("status")
    @Expose
    private
    String  status;


    @SerializedName("message")
    @Expose
    private
    String msg="";


    @SerializedName("code")
    @Expose
    private
    int code;

    @SerializedName("Nutritions")
    @Expose
    private NutritionListModel[]  Nutritions;



    public NutritionListModel[] getNutritions() {
        return Nutritions;
    }

    public void setNutritions(NutritionListModel[] nutritions) {
        Nutritions = nutritions;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
