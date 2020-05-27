package com.kampen.riksSe.api.remote_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generic_Result<T> {


      /*  "status": "success",
        "message": "success",
        "code": 200,*/





    @SerializedName("status")
    @Expose
    private
    String  status;


    @SerializedName("message")
    @Expose
    private
    String msg="";


    /*@SerializedName("resultdate")
    @Expose
    private
    String resultdate;*/

    @SerializedName("result")
    @Expose
    private T  result;


    @SerializedName("code")
    @Expose
    private
    int code;





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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


  /*  public String getResultdate() {
        return resultdate;
    }

    public void setResultdate(String resultdate) {
        this.resultdate = resultdate;
    }*/
}