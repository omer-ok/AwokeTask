package com.kampen.riksSe.payment.PayEx.PayExSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayeeInfo {


    @SerializedName("payeeId")
    @Expose
    private String payeeId;
    @SerializedName("payeeReference")
    @Expose
    private String payeeReference;
    @SerializedName("payeeName")
    @Expose
    private String payeeName;
    @SerializedName("productCategory")
    @Expose
    private String productCategory;
    @SerializedName("orderReference")
    @Expose
    private String orderReference;
    @SerializedName("subsite")
    @Expose
    private String subsite;

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeReference() {
        return payeeReference;
    }

    public void setPayeeReference(String payeeReference) {
        this.payeeReference = payeeReference;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public String getSubsite() {
        return subsite;
    }

    public void setSubsite(String subsite) {
        this.subsite = subsite;
    }

}
