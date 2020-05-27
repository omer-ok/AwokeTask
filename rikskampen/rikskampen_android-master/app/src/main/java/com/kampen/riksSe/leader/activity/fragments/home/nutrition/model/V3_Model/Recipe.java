
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class Recipe  extends RealmObject implements Comparable<Recipe>{

    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    //@PrimaryKey
    private int mId;
    @SerializedName("ingredients")
    private RealmList<Ingredient> mIngredients;
    @SerializedName("media_image")
    private String mMediaImage;
    @SerializedName("media_video")
    private String mMediaVideo;
    @SerializedName("name")
    private String mName;

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public RealmList<Ingredient> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(RealmList<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public String getmMediaImage() {
        return mMediaImage;
    }

    public void setmMediaImage(String mMediaImage) {
        this.mMediaImage = mMediaImage;
    }

    public String getmMediaVideo() {
        return mMediaVideo;
    }

    public void setmMediaVideo(String mMediaVideo) {
        this.mMediaVideo = mMediaVideo;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }


    /*@Override
    public int compareTo(Recipe recipe1,Recipe recipe2) {
        return recipe1.getmName().compareTo(recipe2.getmName());
    }
*/

    @Override
    public int compareTo(Recipe recipe) {
        return 0;
    }
}
