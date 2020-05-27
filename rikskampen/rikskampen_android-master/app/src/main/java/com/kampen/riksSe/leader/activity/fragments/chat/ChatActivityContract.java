package com.kampen.riksSe.leader.activity.fragments.chat;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class ChatActivityContract {


    interface View extends BaseView<Presenter> {

        void setChatTrainerSucess(String message);
        void setChatTrainerFailed(String message);

        void setChatBadgeSucess(String message);
        void setChatBadgeFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getChatDataFromServer(Context context);
        void setChatBadgesData(Context context,String type,String week,String deviceID,String BadgeCount);
    }

}
