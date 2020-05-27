package com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PastActivitiesListModel {


    private List<PastActivityModel> pastActivityModelList;

    public List<PastActivityModel> getPastActivityModelList() {
        return pastActivityModelList;
    }

    public void setPastActivityModelList(List<PastActivityModel> pastActivityModelList) {
        this.pastActivityModelList = pastActivityModelList;
    }
}
