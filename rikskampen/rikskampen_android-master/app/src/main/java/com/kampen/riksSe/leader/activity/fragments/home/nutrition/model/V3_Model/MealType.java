
package com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("unused")
public class MealType  extends RealmObject {

    @SerializedName("id")
    private int mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("name_en")
    private String mNameEn;
    @SerializedName("name_sv")
    private String mNameSv;
    @SerializedName("order")
    private String mOrder;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getNameEn() {
        return mNameEn;
    }

    public void setNameEn(String nameEn) {
        mNameEn = nameEn;
    }

    public String getNameSv() {
        return mNameSv;
    }

    public void setNameSv(String nameSv) {
        mNameSv = nameSv;
    }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }

}
