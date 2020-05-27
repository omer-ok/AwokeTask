package com.kampen.riksSe.ChangePassword;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter{

    @NonNull
    private final ChangePasswordContract.View  mChangePassView;



    public ChangePasswordPresenter(@NonNull ChangePasswordContract.View ChangePassView)
    {
        mChangePassView = checkNotNull(ChangePassView);

    }


    @Override
    public void performChangePasword(String Email,String Password) {

        Repository.getChangePasswordData(Email,Password, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {



                if(mChangePassView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mChangePassView.setChangePaswordSucess(status.getMsg());
                }
                else
                {
                    mChangePassView.setChangePasswordFailed(status.getMsg());
                }

            }
        });



    }
}
