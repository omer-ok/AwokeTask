package com.kampen.riksSe.leader.activity.fragments.chat;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ChatModel {

    String date;
    String text;
    String userID;
    long DateLong;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public long getDateLong() {
        return DateLong;
    }

    public void setDateLong(long dateLong) {
        DateLong = dateLong;
    }
}
