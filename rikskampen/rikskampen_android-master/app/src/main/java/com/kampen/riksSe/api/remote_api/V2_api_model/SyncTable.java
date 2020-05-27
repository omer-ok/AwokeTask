package com.kampen.riksSe.api.remote_api.V2_api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncTable {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("truncate_table")
    @Expose
    private TruncateTable truncateTable;
    @SerializedName("truncate_id")
    @Expose
    private TruncateId truncateId;
    @SerializedName("sync_type")
    @Expose
    private String syncType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TruncateTable getTruncateTable() {
        return truncateTable;
    }

    public void setTruncateTable(TruncateTable truncateTable) {
        this.truncateTable = truncateTable;
    }

    public TruncateId getTruncateId() {
        return truncateId;
    }

    public void setTruncateId(TruncateId truncateId) {
        this.truncateId = truncateId;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

}
