package com.kampen.riksSe.leader.activity.fragments.chat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatDetails {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("trainerName")
    @Expose
    private String trainerName;
    @SerializedName("trainerNumber")
    @Expose
    private String trainerNumber;
    @SerializedName("trainerID")
    @Expose
    private String trainerID;
    @SerializedName("trainerPhoto")
    @Expose
    private   String   trainerPhoto;

    public String getTrainerPhoto() {
        return trainerPhoto;
    }

    public void setTrainerPhoto(String trainerPhoto) {
        this.trainerPhoto = trainerPhoto;
    }

    public String getTrainerNumber() {
        return trainerNumber;
    }

    public void setTrainerNumber(String trainerNumber) {
        this.trainerNumber = trainerNumber;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }




}
