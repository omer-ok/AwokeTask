package com.kampen.riksSe.api.remote_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayEx_Result_obj {

    @SerializedName("method")
    @Expose
    private  String   method;

    @SerializedName("rel")
    @Expose
    private  String   rel;

    @SerializedName("href")
    @Expose
    private  String   href;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @SerializedName("contentType")
    @Expose
    private  String   contentType;




}
