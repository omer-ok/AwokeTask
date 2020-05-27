package com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class weeklyActivities extends RealmObject {



    @SerializedName("weekID")
    @Expose
    private String  weekID;

    @SerializedName("weekName")
    @Expose
    private String  weekName;

    @SerializedName("imagePath")
    @Expose
    private String  imagePath;



   /* @SerializedName("days")
    @Expose
    private ArrayList<Remote_DayActivity> weekList;
*/

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

 /*   public ArrayList<Remote_DayActivity> getWeekList() {
        return weekList;
    }

    public void setWeekList(ArrayList<Remote_DayActivity> weekList) {
        this.weekList = weekList;
    }
*/


}
