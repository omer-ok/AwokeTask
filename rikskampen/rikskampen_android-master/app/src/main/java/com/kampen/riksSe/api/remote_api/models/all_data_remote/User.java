package com.kampen.riksSe.api.remote_api.models.all_data_remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.home.HomeData;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.order_status.OrderData;

public class User {


    @SerializedName("userid")
    @Expose
    private  String  userid;


    @SerializedName("firstName")
    @Expose
    private  String  firstName;

    @SerializedName("lastName")
    @Expose
    private  String  lastName;

    @SerializedName("gender")
    @Expose
    private  String  gender;

    @SerializedName("dateOfBirth")
    @Expose
    private  String  dateOfBirth;

    @SerializedName("email")
    @Expose
    private  String  email;

    @SerializedName("streetAddress")
    @Expose
    private  String  streetAddress;

    @SerializedName("profileImage")
    @Expose
    private  String  profileImage;

    @SerializedName("thumbImage")
    @Expose
    private  String  thumbImage;

    @SerializedName("phonenumber")
    @Expose
    private  String  phonenumber;


    @SerializedName("height")
    @Expose
    private  int  height;

    @SerializedName("height_unit")
    @Expose
    private  String  heightUnit;

    @SerializedName("weight")
    @Expose
    private  int  weight;

    @SerializedName("weight_unit")
    @Expose
    private  String  weightUnit;


    @SerializedName("Home")
    @Expose
    private HomeData Home;


    @SerializedName("Order")
    @Expose
    private OrderData orderData;







    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public HomeData getHome() {
        return Home;
    }

    public void setHome(HomeData home) {
        Home = home;
    }
}
