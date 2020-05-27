
package com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


@SuppressWarnings("unused")
public class TrainingPlans extends RealmObject {

    @SerializedName("plans")
    private RealmList<W_Plans> mWPlans;

    @SerializedName("workout_types")
    private RealmList<WorkoutType> mWorkoutTypes;

    public RealmList<W_Plans> getmWPlans() {
        return mWPlans;
    }

    public void setmWPlans(RealmList<W_Plans> mWPlans) {
        this.mWPlans = mWPlans;
    }

    public RealmList<WorkoutType> getmWorkoutTypes() {
        return mWorkoutTypes;
    }

    public void setmWorkoutTypes(RealmList<WorkoutType> mWorkoutTypes) {
        this.mWorkoutTypes = mWorkoutTypes;
    }
}
