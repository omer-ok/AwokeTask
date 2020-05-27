
package com.kampen.riksSe.leader.activity.modelV3;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Permissions {

    @SerializedName("CameraPermission")
    private boolean CameraPermission;
    @SerializedName("StepCounterPermission")
    private boolean StepCounterPermission;
    @SerializedName("LocationForeground")
    private boolean LocationForeground;
    @SerializedName("LocationBackGround")
    private boolean LocationBackGround;
    @SerializedName("DrawOverOtherApps")
    private boolean DrawOverOtherApps;
    @SerializedName("StepsForegroundService")
    private boolean StepsForegroundService;

    public boolean isCameraPermission() {
        return CameraPermission;
    }

    public void setCameraPermission(boolean cameraPermission) {
        CameraPermission = cameraPermission;
    }

    public boolean isStepCounterPermission() {
        return StepCounterPermission;
    }

    public void setStepCounterPermission(boolean stepCounterPermission) {
        StepCounterPermission = stepCounterPermission;
    }

    public boolean getLocationForeground() {
        return LocationForeground;
    }

    public void setLocationForeground(boolean locationForeground) {
        LocationForeground = locationForeground;
    }

    public boolean getLocationBackGround() {
        return LocationBackGround;
    }

    public void setLocationBackGround(boolean locationBackGround) {
        LocationBackGround = locationBackGround;
    }
    public boolean isDrawOverOtherApps() {
        return DrawOverOtherApps;
    }

    public void setDrawOverOtherApps(boolean drawOverOtherApps) {
        DrawOverOtherApps = drawOverOtherApps;
    }
    public boolean isStepsForegroundService() {
        return StepsForegroundService;
    }

    public void setStepsForegroundService(boolean stepsForegroundService) {
        StepsForegroundService = stepsForegroundService;
    }
}
