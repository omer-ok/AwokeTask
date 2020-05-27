package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchduleSlots {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("trainer_id")
    @Expose
    private Integer trainerId;
    @SerializedName("schedule_date")
    @Expose
    private String scheduleDate;
    @SerializedName("parts_of_day")
    @Expose
    private String partsOfDay;
    @SerializedName("session_starts_at")
    @Expose
    private String sessionStartsAt;
    @SerializedName("session_ends_at")
    @Expose
    private String sessionEndsAt;
    @SerializedName("session_duration_minutes")
    @Expose
    private Integer sessionDurationMinutes;
    @SerializedName("session_pre_cancellation_minutes")
    @Expose
    private Integer sessionPreCancellationMinutes;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getPartsOfDay() {
        return partsOfDay;
    }

    public void setPartsOfDay(String partsOfDay) {
        this.partsOfDay = partsOfDay;
    }

    public String getSessionStartsAt() {
        return sessionStartsAt;
    }

    public void setSessionStartsAt(String sessionStartsAt) {
        this.sessionStartsAt = sessionStartsAt;
    }

    public String getSessionEndsAt() {
        return sessionEndsAt;
    }

    public void setSessionEndsAt(String sessionEndsAt) {
        this.sessionEndsAt = sessionEndsAt;
    }

    public Integer getSessionDurationMinutes() {
        return sessionDurationMinutes;
    }

    public void setSessionDurationMinutes(Integer sessionDurationMinutes) {
        this.sessionDurationMinutes = sessionDurationMinutes;
    }

    public Integer getSessionPreCancellationMinutes() {
        return sessionPreCancellationMinutes;
    }

    public void setSessionPreCancellationMinutes(Integer sessionPreCancellationMinutes) {
        this.sessionPreCancellationMinutes = sessionPreCancellationMinutes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
