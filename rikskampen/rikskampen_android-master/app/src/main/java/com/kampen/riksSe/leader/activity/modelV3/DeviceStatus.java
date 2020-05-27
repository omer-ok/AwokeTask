
package com.kampen.riksSe.leader.activity.modelV3;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DeviceStatus {

    @SerializedName("battery")
    private String mBattery;

    public String getBattery() {
        return mBattery;
    }

    public void setBattery(String battery) {
        mBattery = battery;
    }

}
