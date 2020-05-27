package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelDayList;

import java.util.List;

public class DaySchduleList {

    int id;
    int trainerID;
    String scheduleDate;
    String partsOfDay;
    String sessionStartsAt;
    String sessionEndsAt;
    List<String> DayTime;
    String DayStatus;
    int sessionDurationMinutes;
    int sessionPreCancellationMinutes;

    public List<String> getDayTime() {
        return DayTime;
    }

    public void setDayTime(List<String> dayTime) {
        DayTime = dayTime;
    }

    public String getDayStatus() {
        return DayStatus;
    }

    public void setDayStatus(String dayStatus) {
        DayStatus = dayStatus;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
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

    public int getSessionDurationMinutes() {
        return sessionDurationMinutes;
    }

    public void setSessionDurationMinutes(int sessionDurationMinutes) {
        this.sessionDurationMinutes = sessionDurationMinutes;
    }

    public int getSessionPreCancellationMinutes() {
        return sessionPreCancellationMinutes;
    }

    public void setSessionPreCancellationMinutes(int sessionPreCancellationMinutes) {
        this.sessionPreCancellationMinutes = sessionPreCancellationMinutes;
    }
}
