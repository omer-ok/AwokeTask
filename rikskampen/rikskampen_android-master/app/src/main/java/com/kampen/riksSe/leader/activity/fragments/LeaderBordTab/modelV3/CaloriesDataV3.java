package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class CaloriesDataV3 extends RealmObject implements Comparable<CaloriesDataV3>{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("profileImage")
    @Expose
    private String profileImage;
    @SerializedName("thumbImage")
    @Expose
    private String thumbImage;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("count")
    @Expose
    private double count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public int compareTo(CaloriesDataV3 caloriesDataV3) {
        if (getRank() == 0 || caloriesDataV3.getRank() == 0)
            return 0;
        return Integer.compare(getRank(),caloriesDataV3.getRank());
    }
}
