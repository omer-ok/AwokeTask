package com.kampen.riksSe.login.ModelsV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResultModel {

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
    private RemoteUser  result;


    @SerializedName("code")
    @Expose
    private
    int code;

    @SerializedName("roles")
    @Expose
    private
    List<UserRoles> roles;

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

    public RemoteUser getResult() {
        return result;
    }

    public void setResult(RemoteUser result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

}
