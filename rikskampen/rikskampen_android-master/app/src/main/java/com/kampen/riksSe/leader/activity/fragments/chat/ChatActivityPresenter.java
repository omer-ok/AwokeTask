package com.kampen.riksSe.leader.activity.fragments.chat;

import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ChatActivityPresenter implements ChatActivityContract.Presenter{


    @NonNull
    private final ChatActivityContract.View  mChatView;



    public ChatActivityPresenter(@NonNull ChatActivityContract.View ChatView)
    {
        mChatView = checkNotNull(ChatView);

    }

    @Override
    public void getChatDataFromServer(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        String token = "bearer " + user.getToken();


        Repository.getChatData(user.getId(), token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mChatView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mChatView.setChatTrainerSucess(status.getMsg());
                }
                else
                {
                    mChatView.setChatTrainerFailed(status.getMsg());
                }


            }
        });

    }

    @Override
    public void setChatBadgesData(Context context,String type,String week,String deviceID,String BadgeCount) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        String token = "bearer " + user.getToken();


        Repository.getChatBadgeData(user.getId(),type,week,deviceID,BadgeCount, token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mChatView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mChatView.setChatBadgeSucess(status.getMsg());
                }
                else
                {
                    mChatView.setChatBadgeFailed(status.getMsg());
                }


            }
        });

    }


}
