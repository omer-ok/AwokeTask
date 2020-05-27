package com.kampen.riksSe.payment.PayEx.PayExSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentSend {

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("intent")
    @Expose
    private String intent;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("prices")
    @Expose
    private List<Price> prices = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("payerReference")
    @Expose
    private String payerReference;
    @SerializedName("generatePaymentToken")
    @Expose
    private Boolean generatePaymentToken;
    @SerializedName("userAgent")
    @Expose
    private String userAgent;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("payeeInfo")
    @Expose
    private PayeeInfo payeeInfo;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayerReference() {
        return payerReference;
    }

    public void setPayerReference(String payerReference) {
        this.payerReference = payerReference;
    }

    public Boolean getGeneratePaymentToken() {
        return generatePaymentToken;
    }

    public void setGeneratePaymentToken(Boolean generatePaymentToken) {
        this.generatePaymentToken = generatePaymentToken;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public PayeeInfo getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfo payeeInfo) {
        this.payeeInfo = payeeInfo;
    }


}
