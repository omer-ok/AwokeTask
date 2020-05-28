package com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.ModelV3.LiveVideoCallToken;

public class LiveVideoCallActivityContract {

    interface View extends BaseView<Presenter> {

        void SetLiveVideoCallTokenSucess(String mesage, LiveVideoCallToken liveVideoCallToken);
        void SetLiveVideoCallTokenFiled(String mesage);

    }

    interface Presenter extends BasePresenter {


        void getLiveVideoCallAccessToken(Context context,String TrainerID,String userID);

    }

}
