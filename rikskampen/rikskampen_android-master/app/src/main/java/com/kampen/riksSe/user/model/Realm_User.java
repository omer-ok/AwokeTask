package com.kampen.riksSe.user.model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Realm_User extends RealmObject {



    private  String  id;

    private  String  token;

    private  String  f_name;

    private  String  l_name;

    private  String  _email;

    private  String  _pass;

    private  String  profile_image;

    private  String  thumbImage;

    private  String  phonenumber;

    private  String  streetAddress;

    private  int userRoleID;

    private   boolean isSyncedWithServer;

    private  int  age;

    private  String  dob;

    private  String cvc;

    private  double height_in_cm;

    private  String  height_unit;

    private   double     weight;

    private   double     Goalweight;

    private   double     waist;

    private  String  weight_unit;

    private   String    user_gender;

    private   String    trainerName;

    private   String   trainerPhoto;

    private   String    trainerNumber;

    private   String    trainerID;

    private  String      paymentMethods;

    private  String      paymentStatus;


    private  String      location;


    private  String      activityTime;




    private  String      ActivityImage;

    private  String      ActivityWeight;


    private  String      ActivityWaist;



    private Boolean alreadyLogin;

    private  String      contestStartDate;



    private  String      contestEndDate;

    private Boolean isLogin;

    private  int      contestID;


    private  String      mDesignation;



    public String getContestStartDate() {
        return contestStartDate;
    }

    public void setContestStartDate(String contestStartDate) {
        this.contestStartDate = contestStartDate;
    }
    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }
    public double getGoalweight() {
        return Goalweight;
    }

    public void setGoalweight(double goalweight) {
        Goalweight = goalweight;
    }
    public String getContestEndDate() {
        return contestEndDate;
    }

    public void setContestEndDate(String contestEndDate) {
        this.contestEndDate = contestEndDate;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }
    public Boolean getAlreadyLogin() {
        return alreadyLogin;
    }

    public void setAlreadyLogin(Boolean alreadyLogin) {
        this.alreadyLogin = alreadyLogin;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public String getTrainerPhoto() {
        return trainerPhoto;
    }

    public void setTrainerPhoto(String trainerPhoto) {
        this.trainerPhoto = trainerPhoto;
    }

    private   byte []  profilePicData;

    private String activity_title;
    private String activity_type_id;
    private String steps_count;
    private String day_name;
    private String stars_count;
    private String calories;
    private String user_activity_time;
    private String activity_image;
    private String activity_lat;
    private String activity_long;
    private String activity_location;
    private String activity_weight;
    private String Distance;

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }


    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public String getActivity_type_id() {
        return activity_type_id;
    }

    public void setActivity_type_id(String activity_type_id) {
        this.activity_type_id = activity_type_id;
    }

    public String getSteps_count() {
        return steps_count;
    }

    public void setSteps_count(String steps_count) {
        this.steps_count = steps_count;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getStars_count() {
        return stars_count;
    }

    public void setStars_count(String stars_count) {
        this.stars_count = stars_count;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getUser_activity_time() {
        return user_activity_time;
    }

    public void setUser_activity_time(String user_activity_time) {
        this.user_activity_time = user_activity_time;
    }

    public String getActivity_image() {
        return activity_image;
    }

    public void setActivity_image(String activity_image) {
        this.activity_image = activity_image;
    }

    public String getActivity_lat() {
        return activity_lat;
    }

    public void setActivity_lat(String activity_lat) {
        this.activity_lat = activity_lat;
    }

    public String getActivity_long() {
        return activity_long;
    }

    public void setActivity_long(String activity_long) {
        this.activity_long = activity_long;
    }

    public String getActivity_location() {
        return activity_location;
    }

    public void setActivity_location(String activity_location) {
        this.activity_location = activity_location;
    }
    public String getActivity_weight() {
        return activity_weight;
    }

    public void setActivity_weight(String activity_weight) {
        this.activity_weight = activity_weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }



    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int role) {
        this.userRoleID = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }





    public double getHeight_in_cm() {
        return height_in_cm;
    }

    public void setHeight_in_cm(double height_in_cm) {
        this.height_in_cm = height_in_cm;
    }

    public String getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(String height_unit) {
        this.height_unit = height_unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public byte[] getProfilePicData() {
        return profilePicData;
    }

    public void setProfilePicData(byte[] profilePicData) {
        this.profilePicData = profilePicData;
    }


    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPass() {
        return _pass;
    }

    public void setPass(String _pass) {
        this._pass = _pass;
    }

    public boolean isSyncedWithServer() {
        return isSyncedWithServer;
    }

    public void setSyncedWithServer(boolean syncedWithServer) {
        isSyncedWithServer = syncedWithServer;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, RealmObject.class);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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


    public String getActivityWeight() {
        return ActivityWeight;
    }

    public void setActivityWeight(String activityWeight) {
        ActivityWeight = activityWeight;
    }

    public String getActivityWaist() {
        return ActivityWaist;
    }

    public void setActivityWaist(String activityWaist) {
        ActivityWaist = activityWaist;
    }

    public int getContestID() {
        return contestID;
    }

    public void setContestID(int contestID) {
        this.contestID = contestID;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }




}
