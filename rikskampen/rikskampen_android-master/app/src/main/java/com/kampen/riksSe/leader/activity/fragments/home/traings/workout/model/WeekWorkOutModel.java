package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.model;

import io.realm.RealmObject;

public class WeekWorkOutModel extends RealmObject {


    private  String  imagePath;

    private   int tempImage;

    private   String   workOutName;

    private    String    id;




    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTempImage() {
        return tempImage;
    }

    public void setTempImage(int tempImage) {
        this.tempImage = tempImage;
    }

    public String getWorkOutName() {
        return workOutName;
    }

    public void setWorkOutName(String workOutName) {
        this.workOutName = workOutName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
