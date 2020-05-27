
package com.kampen.riksSe.UpdateAppVersion;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class AppUpdate extends RealmObject {

    @SerializedName("id")
    private int mId;
    @SerializedName("is_force_update")
    private boolean mIsForceUpdate;
    @SerializedName("version")
    private String mVersion;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public boolean getIsForceUpdate() {
        return mIsForceUpdate;
    }

    public void setIsForceUpdate(boolean isForceUpdate) {
        mIsForceUpdate = isForceUpdate;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

}
