package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ContestantUser extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("profileImage")
    @Expose
    private String profileImage;
    @SerializedName("thumbImage")
    @Expose
    private String thumbImage;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("national_id")
    @Expose
    private String nationalId;
    @SerializedName("stepCount")
    @Expose
    private String stepsCount;
    @SerializedName("starCount")
    @Expose
    private String starsCount;
    @SerializedName("caloriesCount")
    @Expose
    private String calories;

    @SerializedName("distanceSum")
    @Expose
    private String distance;

    @SerializedName("distanceUnit")
    @Expose
    private String distanceUnit;

    @SerializedName("caloriesUnit")
    @Expose
    private String caloriesUnit;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("starsRank")
    @Expose
    private String starsRank;

    @SerializedName("stepsRank")
    @Expose
    private String stepsRank;

    @SerializedName("caloriesRank")
    @Expose
    private String caloriesRank;



    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public String getCaloriesUnit() {
        return caloriesUnit;
    }

    public void setCaloriesUnit(String caloriesUnit) {
        this.caloriesUnit = caloriesUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(String stepsCount) {
        this.stepsCount = stepsCount;
    }

    public String getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(String starsCount) {
        this.starsCount = starsCount;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStarsRank() {
        return starsRank;
    }

    public void setStarsRank(String starsRank) {
        this.starsRank = starsRank;
    }

    public String getStepsRank() {
        return stepsRank;
    }

    public void setStepsRank(String stepsRank) {
        this.stepsRank = stepsRank;
    }

    public String getCaloriesRank() {
        return caloriesRank;
    }

    public void setCaloriesRank(String caloriesRank) {
        this.caloriesRank = caloriesRank;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}