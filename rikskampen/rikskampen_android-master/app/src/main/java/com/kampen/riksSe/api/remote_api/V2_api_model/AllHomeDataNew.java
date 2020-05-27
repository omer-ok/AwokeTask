
package com.kampen.riksSe.api.remote_api.V2_api_model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@SuppressWarnings("unused")
public class AllHomeDataNew {

    @SerializedName("code")
    private int mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private Result mResult;
    @SerializedName("status")
    private String mStatus;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
