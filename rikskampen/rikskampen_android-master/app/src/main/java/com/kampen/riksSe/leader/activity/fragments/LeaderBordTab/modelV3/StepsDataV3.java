package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;

import io.realm.RealmObject;

public class StepsDataV3 extends RealmObject implements Comparable<StepsDataV3>{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("profileImage")
    @Expose
    private String profileImage;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("rank")
    @Expose
    private int rank;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(StepsDataV3 stepsDataV3) {
        if (getRank() == 0 || stepsDataV3.getRank() == 0)
            return 0;
        return Integer.compare(getRank(),stepsDataV3.getRank());
    }
}
