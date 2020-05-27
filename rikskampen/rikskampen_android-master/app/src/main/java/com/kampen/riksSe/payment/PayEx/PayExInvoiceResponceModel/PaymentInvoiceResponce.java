package com.kampen.riksSe.payment.PayEx.PayExInvoiceResponceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentInvoiceResponce {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private double number;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("instrument")
    @Expose
    private String instrument;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("intent")
    @Expose
    private String intent;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("prices")
    @Expose
    private PricesInvoiceResponce prices;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("payerReference")
    @Expose
    private String payerReference;
    @SerializedName("initiatingSystemUserAgent")
    @Expose
    private String initiatingSystemUserAgent;
    @SerializedName("userAgent")
    @Expose
    private String userAgent;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("urls")
    @Expose
    private UrlsInvoiceResponce urls;
    @SerializedName("payeeInfo")
    @Expose
    private PayeeInfoInvoiceResponce payeeInfo;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PricesInvoiceResponce getPrices() {
        return prices;
    }

    public void setPrices(PricesInvoiceResponce prices) {
        this.prices = prices;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public String getInitiatingSystemUserAgent() {
        return initiatingSystemUserAgent;
    }

    public void setInitiatingSystemUserAgent(String initiatingSystemUserAgent) {
        this.initiatingSystemUserAgent = initiatingSystemUserAgent;
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

    public UrlsInvoiceResponce getUrls() {
        return urls;
    }

    public void setUrls(UrlsInvoiceResponce urls) {
        this.urls = urls;
    }

    public PayeeInfoInvoiceResponce getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfoInvoiceResponce payeeInfo) {
        this.payeeInfo = payeeInfo;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }


}
