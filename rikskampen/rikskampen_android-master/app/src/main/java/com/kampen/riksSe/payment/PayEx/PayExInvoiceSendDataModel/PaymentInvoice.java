package com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentInvoice {

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
    private List<PriceInvoice> prices = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("payerReference")
    @Expose
    private String payerReference;
    @SerializedName("userAgent")
    @Expose
    private String userAgent;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("urls")
    @Expose
    private UrlsInvoice urls;
    @SerializedName("payeeInfo")
    @Expose
    private PayeeInfoInvoice payeeInfo;

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

    public List<PriceInvoice> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceInvoice> prices) {
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

    public UrlsInvoice getUrls() {
        return urls;
    }

    public void setUrls(UrlsInvoice urls) {
        this.urls = urls;
    }

    public PayeeInfoInvoice getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfoInvoice payeeInfo) {
        this.payeeInfo = payeeInfo;
    }

}
