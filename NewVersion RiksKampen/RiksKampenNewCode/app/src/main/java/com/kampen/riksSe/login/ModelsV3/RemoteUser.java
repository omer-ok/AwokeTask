package com.kampen.riksSe.login.ModelsV3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RemoteUser  {


    /*"token": "OTlrdUZzTTFTV0UyT21JOUREN1lnQU9KQmFFWmN3VXdFVE5zVmtyTQ==",
            "id": 31,
            "firstname": "umer",
            "lastname": "javaid",
            "email": "you1@gmail.com",
            "gender": null,
            "dateOfBirth": null,
            "streetAddress": null,
            "phonenumber": null,
            "profileImage": "",
            "thumbImage": "",
            "height": "0.00",
            "weight": "0.00",
            "role_id": "3"*/


    @SerializedName("id")
    @Expose
    private  String   id;

    @SerializedName("token")
    @Expose
    private  String   token;

    @SerializedName("password")
    @Expose
    private  String   password;

    @SerializedName("firstname")
    @Expose
    private  String   firstname;
    @SerializedName("lastname")
    @Expose
    private  String   lastname;
    @SerializedName("email")
    @Expose
    private  String   email;

    @SerializedName("gender")
    @Expose
    private  String   gender;

    @SerializedName("dateOfBirth")
    @Expose
    private  String   dateOfBirth;


    @SerializedName("national_id")
    @Expose
    private String CVC;

    @SerializedName("streetAddress")
    @Expose
    private  String   streetAddress;

    @SerializedName("phonenumber")
    @Expose
    private  String   phonenumber;



    @SerializedName("profileImage")
    @Expose
    private  String   profileImage;

    @SerializedName("thumbImage")
    @Expose
    private  String   thumbImage;

    @SerializedName("height")
    @Expose
    private  String   height;

    @SerializedName("weight")
    @Expose
    private  String   weight;

    @SerializedName("weight_goal")
    @Expose
    private  String   Goalweight;

    @SerializedName("email_verified_at")
    @Expose
    private  String email_verified_at;
    @SerializedName("role_id")
    @Expose
    private  int      mUserRoleID;
    @SerializedName("created_at")
    @Expose
    private  String      created_at;
    @SerializedName("updated_at")
    @Expose
    private  String      updated_at;

    @SerializedName("trainerName")
    @Expose
    private  String      trainerName;

    @SerializedName("trainerNumber")
    @Expose
    private  String      trainerNumber;

    @SerializedName("trainerID")
    @Expose
    private  String      trainerID;

    @SerializedName("trainerPhoto")
    @Expose
    private  String      trainerPhoto;

    @SerializedName("stepCount")
    @Expose
    private  String      stepCount;

    @SerializedName("starCount")
    @Expose
    private  String      StarCount;

    @SerializedName("payment_methods")
    @Expose
    private  String      paymentMethods;

    @SerializedName("payment_status")
    @Expose
    private  String      paymentStatus;

    @SerializedName("location")
    @Expose
    private  String      location;

    @SerializedName("activityTime")
    @Expose
    private  String      activityTime;

    @SerializedName("ActivityImage")
    @Expose
    private  String      ActivityImage;

    @SerializedName("waist")
    @Expose
    private  String      waist;

    @SerializedName("activityWeight")
    @Expose
    private  String      activityWeight;

    @SerializedName("alreadyLogin")
    @Expose
    private  Boolean      alreadyLogin;

    @SerializedName("isLogin")
    @Expose
    private  Boolean      isLogin;


    @SerializedName("isDeveloper")
    @Expose
    private  Boolean      isDeveloper;

    @SerializedName("contestStartDate")
    @Expose
    private  String      contestStartDate;



    @SerializedName("contestEndDate")
    @Expose
    private  String      contestEndDate;

    @SerializedName("contestID")
    @Expose
    private  int      contestID;


    @SerializedName("designation")
    @Expose
    private  String      mDesignation;

    @SerializedName("consteants")
    @Expose
    private List<Contestant> contestant;

    public String getContestStartDate() {
        return contestStartDate;
    }

    public void setContestStartDate(String contestStartDate) {
        this.contestStartDate = contestStartDate;
    }

    public String getContestEndDate() {
        return contestEndDate;
    }

    public void setContestEndDate(String contestEndDate) {
        this.contestEndDate = contestEndDate;
    }
    public Boolean getDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(Boolean developer) {
        isDeveloper = developer;
    }

    public Boolean getAlreadyLogin() {
        return alreadyLogin;
    }

    public void setAlreadyLogin(Boolean alreadyLogin) {
        this.alreadyLogin = alreadyLogin;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getStepCount() {
        return stepCount;
    }

    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getStarCount() {
        return StarCount;
    }

    public void setStarCount(String starCount) {
        StarCount = starCount;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getTrainerPhoto() {
        return trainerPhoto;
    }

    public void setTrainerPhoto(String trainerPhoto) {
        this.trainerPhoto = trainerPhoto;
    }

    public int getUserRoleID() {
        return mUserRoleID;
    }

    public void setUserRoleID(int role) {
        this.mUserRoleID = role;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityImage() {
        return ActivityImage;
    }

    public void setActivityImage(String activityImage) {
        ActivityImage = activityImage;
    }


    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getGoalweight() {
        return Goalweight;
    }

    public void setGoalweight(String goalweight) {
        Goalweight = goalweight;
    }

    public String getActivityWeight() {
        return activityWeight;
    }

    public void setActivityWeight(String activityWeight) {
        this.activityWeight = activityWeight;
    }

    public int getContestID() {
        return contestID;
    }

    public void setContestID(int contestID) {
        this.contestID = contestID;
    }

    public List<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

}
