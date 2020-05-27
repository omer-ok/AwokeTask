package com.kampen.riksSe.payment.PayEx.PayExSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {


    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("vatAmount")
    @Expose
    private Integer vatAmount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Integer vatAmount) {
        this.vatAmount = vatAmount;
    }

}
