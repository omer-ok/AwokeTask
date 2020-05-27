package com.kampen.riksSe.payment.PayEx.PromoCodeModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCodeData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("discounted_package_price")
    @Expose
    private Double discountedPackagePrice;
    @SerializedName("discounted_actual_price")
    @Expose
    private Double discountedActualPrice;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getDiscountedPackagePrice() {
        return discountedPackagePrice;
    }

    public void setDiscountedPackagePrice(Double discountedPackagePrice) {
        this.discountedPackagePrice = discountedPackagePrice;
    }

    public Double getDiscountedActualPrice() {
        return discountedActualPrice;
    }

    public void setDiscountedActualPrice(Double discountedActualPrice) {
        this.discountedActualPrice = discountedActualPrice;
    }

}
