package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyDiaryResponseV3 {

    @SerializedName("status")
    @Expose
    private
    String  status;


    @SerializedName("message")
    @Expose
    private
    String msg="";



    @SerializedName("result")
    @Expose
    private List<diaries> result;


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

    public List<diaries> getResult() {
        return result;
    }

    public void setResult(List<diaries> result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @SerializedName("code")
    @Expose
    private
    int code;
}
