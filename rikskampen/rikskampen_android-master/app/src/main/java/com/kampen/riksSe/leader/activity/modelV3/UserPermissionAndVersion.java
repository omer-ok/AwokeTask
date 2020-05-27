
package com.kampen.riksSe.leader.activity.modelV3;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserPermissionAndVersion {

    @SerializedName("agent")
    private String mAgent;
    @SerializedName("app_version")
    private String mAppVersion;
    @SerializedName("ip_address")
    private String mIpAddress;
    @SerializedName("operating_system")
    private String mOperatingSystem;
    @SerializedName("operating_system_version")
    private String mOperatingSystemVersion;
    @SerializedName("permissions")
    private Permissions mPermissions;
    @SerializedName("specifications")
    private Specifications mSpecifications;
    @SerializedName("device_status")
    private DeviceStatus mDeviceStatus;
    @SerializedName("timezone")
    private String mTimezone;
    @SerializedName("user_id")
    private int mUserId;

    public String getAgent() {
        return mAgent;
    }

    public void setAgent(String agent) {
        mAgent = agent;
    }

    public String getAppVersion() {
        return mAppVersion;
    }

    public void setAppVersion(String appVersion) {
        mAppVersion = appVersion;
    }

    public DeviceStatus getDeviceStatus() {
        return mDeviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        mDeviceStatus = deviceStatus;
    }

    public Object getIpAddress() {
        return mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        mIpAddress = ipAddress;
    }

    public String getOperatingSystem() {
        return mOperatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        mOperatingSystem = operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return mOperatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        mOperatingSystemVersion = operatingSystemVersion;
    }

    public Permissions getPermissions() {
        return mPermissions;
    }

    public void setPermissions(Permissions permissions) {
        mPermissions = permissions;
    }

    public Specifications getSpecifications() {
        return mSpecifications;
    }

    public void setSpecifications(Specifications specifications) {
        mSpecifications = specifications;
    }

    public Object getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

}
