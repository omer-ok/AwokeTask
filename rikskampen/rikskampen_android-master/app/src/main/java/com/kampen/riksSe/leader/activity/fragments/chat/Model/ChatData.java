package com.kampen.riksSe.leader.activity.fragments.chat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatData {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("result")
    @Expose
    private ChatDetails chatDetails;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ChatDetails getChatDetails() {
        return chatDetails;
    }

    public void setChatDetails(ChatDetails chatDetails) {
        this.chatDetails = chatDetails;
    }

}
