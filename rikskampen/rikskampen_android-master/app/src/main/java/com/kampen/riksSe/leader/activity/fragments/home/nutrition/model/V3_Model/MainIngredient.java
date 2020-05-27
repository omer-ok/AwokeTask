
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


@SuppressWarnings("unused")
public class MainIngredient  extends RealmObject {

    @SerializedName("id")
    private int mId;
    @SerializedName("recipes")
    private RealmList<Recipe> mRecipes;
    @SerializedName("title_en")
    private String mTitleEn;
    @SerializedName("title_sv")
    private String mTitleSv;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public RealmList<Recipe> getRecipes() {
        return mRecipes;
    }

    public void setRecipes(RealmList<Recipe> recipes) {
        mRecipes = recipes;
    }

    public String getTitleEn() {
        return mTitleEn;
    }

    public void setTitleEn(String titleEn) {
        mTitleEn = titleEn;
    }

    public String getTitleSv() {
        return mTitleSv;
    }

    public void setTitleSv(String titleSv) {
        mTitleSv = titleSv;
    }

}
