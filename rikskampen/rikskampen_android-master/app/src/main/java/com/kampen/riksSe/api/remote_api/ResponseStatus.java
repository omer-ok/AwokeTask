package com.kampen.riksSe.api.remote_api;

import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateId;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateTable;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyResult;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;

import java.util.List;

public abstract class ResponseStatus {



    private
    String  status;

    private
    String msg="";

    private
    int code;


    private Boolean loginStatus;

    private Boolean isDeveloper;

    private String chatBadgeCount;

    private double ordernumber;

    private String discountedPacagePrice;

    private String discountedActualPrice;

    private String OTPCODE;

    private String TodayStepsFromGoogleFit;

    private List<AllergyResult> AllergiesName;

    private String NutritionPDFLINK;

    private TruncateId truncateId;

    private TruncateTable truncateTable;

    private int mUserRoleId;

    List<SchduleSlots> ContestantDaySchdulesSlotsList;

    public List<SchduleSlots> getContestantDaySchdulesSlotsList() {
        return ContestantDaySchdulesSlotsList;
    }

    public void setContestantDaySchdulesSlotsList(List<SchduleSlots> contestantDaySchdulesSlotsList) {
        ContestantDaySchdulesSlotsList = contestantDaySchdulesSlotsList;
    }



    public String getTodayStepsFromGoogleFit() {
        return TodayStepsFromGoogleFit;
    }

    public void setTodayStepsFromGoogleFit(String todayStepsFromGoogleFit) {
        TodayStepsFromGoogleFit = todayStepsFromGoogleFit;
    }

    public TruncateId getTruncateId() {
        return truncateId;
    }

    public void setTruncateId(TruncateId truncateId) {
        this.truncateId = truncateId;
    }

    public TruncateTable getTruncateTable() {
        return truncateTable;
    }

    public void setTruncateTable(TruncateTable truncateTable) {
        this.truncateTable = truncateTable;
    }

    public String getOTPCODE() {
        return OTPCODE;
    }

    public void setOTPCODE(String OTPCODE) {
        this.OTPCODE = OTPCODE;
    }

    public String getChatBadgeCount() {
        return chatBadgeCount;
    }

    public void setChatBadgeCount(String chatBadgeCount) {
        this.chatBadgeCount = chatBadgeCount;
    }


    public Boolean getDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(Boolean developer) {
        isDeveloper = developer;
    }

    public String getDiscountedActualPrice() {
        return discountedActualPrice;
    }


    public void setDiscountedActualPrice(String discountedActualPrice) {
        this.discountedActualPrice = discountedActualPrice;
    }

    public String getDiscountedPacagePrice() {
        return discountedPacagePrice;
    }

    public void setDiscountedPacagePrice(String discountedPacagePrice) {
        this.discountedPacagePrice = discountedPacagePrice;
    }

    public double getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(double ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getNutritionPDFLINK() {
        return NutritionPDFLINK;
    }

    public void setNutritionPDFLINK(String nutritionPDFLINK) {
        NutritionPDFLINK = nutritionPDFLINK;
    }
    private  Object  data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public abstract  void onResult(ResponseStatus status);


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public List<AllergyResult> getAllergiesName() {
        return AllergiesName;
    }

    public void setAllergiesName(List<AllergyResult> allergiesName) {
        AllergiesName = allergiesName;
    }
    public int getmUserRoleId() {
        return mUserRoleId;
    }

    public void setmUserRoleId(int mUserRoleId) {
        this.mUserRoleId = mUserRoleId;
    }

}
