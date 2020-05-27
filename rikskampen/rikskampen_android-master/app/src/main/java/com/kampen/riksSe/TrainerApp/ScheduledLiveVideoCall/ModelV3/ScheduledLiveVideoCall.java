package com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ScheduledLiveVideoCall extends RealmObject implements Comparable<ScheduledLiveVideoCall>{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slot_id")
    @Expose
    private Integer slotID;
    @SerializedName("trainer_id")
    @Expose
    private Integer trainerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("contest_id")
    @Expose
    private Integer contestId;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("session_starts_at")
    @Expose
    private String sessionStartsAt;
    @SerializedName("session_ends_at")
    @Expose
    private String sessionEndsAt;
    @SerializedName("session_duration_minutes")
    @Expose
    private Integer durationInMinutes;
    @SerializedName("session_pre_cancellation_minutes")
    @Expose
    private Integer sessionPreCancellationMinutes;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("contestant")
    @Expose
    private Contestant contestant;
    @SerializedName("trainer")
    @Expose
    private Trainer trainer ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlotID() {
        return slotID;
    }

    public void setSlotID(Integer slotID) {
        this.slotID = slotID;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getSessionPreCancellationMinutes() {
        return sessionPreCancellationMinutes;
    }

    public void setSessionPreCancellationMinutes(Integer sessionPreCancellationMinutes) {
        this.sessionPreCancellationMinutes = sessionPreCancellationMinutes;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    @Override
    public int compareTo(ScheduledLiveVideoCall scheduledLiveVideoCall) {

        if (getSessionStartsAt() == null || scheduledLiveVideoCall.getSessionStartsAt() == null)
            return 0;
        return getSessionStartsAt().compareTo(scheduledLiveVideoCall.getSessionStartsAt());
    }

}
