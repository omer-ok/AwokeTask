package com.kampen.riksSe.api.remote_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayEx_Result {


    @SerializedName("token")
    @Expose
    private  String   token;

    @SerializedName("input")
    @Expose
    private  String   input;

    @SerializedName("operations")
    @Expose
    private  PayEx_Result_obj   operations;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public PayEx_Result_obj getOperations() {
        return operations;
    }

    public void setOperations(PayEx_Result_obj operations) {
        this.operations = operations;
    }






}
