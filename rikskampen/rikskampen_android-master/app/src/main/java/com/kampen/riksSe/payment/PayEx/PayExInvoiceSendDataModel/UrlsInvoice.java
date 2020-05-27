package com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UrlsInvoice {

    @SerializedName("completeUrl")
    @Expose
    private String completeUrl;
    @SerializedName("cancelUrl")
    @Expose
    private String cancelUrl;
    @SerializedName("callbackUrl")
    @Expose
    private String callbackUrl;
    @SerializedName("hostUrls")
    @Expose
    private List<String> hostUrls = null;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("termsOfServiceUrl")
    @Expose
    private String termsOfServiceUrl;

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<String> getHostUrls() {
        return hostUrls;
    }

    public void setHostUrls(List<String> hostUrls) {
        this.hostUrls = hostUrls;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

}
