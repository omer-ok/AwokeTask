package com.kampen.riksSe.signup;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SignupPresenter implements SignupContract.Presenter{



    @NonNull
    private final SignupContract.View  mSignupView;



    public SignupPresenter(@NonNull SignupContract.View loginView)
    {
        mSignupView = checkNotNull(loginView);

    }




    @Override
    public void performSign_up(String f_name, String l_name, String user_pass, String email, String gender, String dob, String hight, String weight) {

        Repository.signUpUser(f_name, l_name,user_pass,email,gender,dob,hight,weight, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mSignupView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mSignupView.setSignup(status.getMsg());

                }
                else
                {

                    mSignupView.setSignupFailed(status.getMsg());

                }



            }
        });
    }

    @Override
    public String performOFflineSighUp(String fName, String lName, String Email, String Pass) {








        return Repository.addUserLocalWithoutAPI(fName,lName, Email, Pass);
    }
}
