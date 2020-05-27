package com.kampen.riksSe.leader.activity.fragments.home.traings.models;

import io.realm.RealmObject;

public class Workout_Model extends RealmObject {


    private
    String     id;

    private
    String     trainingId;

    private
    String     type;

    private
    MediaModel     mediaModel;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MediaModel getMediaModel() {
        return mediaModel;
    }

    public void setMediaModel(MediaModel mediaModel) {
        this.mediaModel = mediaModel;
    }
}
