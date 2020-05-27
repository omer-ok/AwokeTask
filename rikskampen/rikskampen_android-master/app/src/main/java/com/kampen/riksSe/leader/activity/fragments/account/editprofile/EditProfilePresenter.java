package com.kampen.riksSe.leader.activity.fragments.account.editprofile;

import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;


import java.io.File;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class EditProfilePresenter implements EditProfileContract.Presenter{



    @NonNull
    private final EditProfileContract.View  mEditProfileView;



    public EditProfilePresenter(@NonNull EditProfileContract.View loginView)
    {

                  mEditProfileView = checkNotNull(loginView);

    }






    @Override
    public void performEditProfile(String f_name, String l_name, String user_pass, String email) {


        Repository.editUser(f_name,  l_name,user_pass,email, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mEditProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                     mEditProfileView.setEditProfile(status.getMsg());
                }
                else
                {
                    mEditProfileView.setEditProfileFailed(status.getMsg());
                }


            }
        });

    }

    @Override
    public void updateProfile(String userID,String token) {
        Repository.getUserProfile(userID,token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mEditProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mEditProfileView.UpdateEditProfileSucess(status.getMsg());
                }
                else
                {
                    mEditProfileView.UpdateEditProfileFailed(status.getMsg());
                }


            }
        });
    }


    public void updateUserProfileLocal(Realm_User user, File imagePath, String fname, String lname, String pass, String CVC, String gender, String dob, String hight, String weight, String waist,String GoalWeight, Context conn){


        String  token="bearer "+user.getToken();

        Repository.editUserProfileWeb(user,token,imagePath,fname,lname,pass,CVC,gender,dob,hight,weight,waist,GoalWeight, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mEditProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mEditProfileView.setEditProfile(status.getMsg());
                }
                else
                {
                    mEditProfileView.setEditProfileFailed(status.getMsg());
                }


            }
        });

    }

    public void updateUserProfileLocalWithOutImage(Realm_User user, String fname, String lname, String gender, String dob, String hight, String weight, String waist,String GoalWeight, Context conn){



        Repository.UpdatProfileWithoutImage(user,fname,lname,dob,gender,hight,weight,waist,GoalWeight, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mEditProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mEditProfileView.setEditProfile(status.getMsg());
                }
                else
                {
                    mEditProfileView.setEditProfileFailed(status.getMsg());
                }


            }
        });

    }




    public  void setUserProfileImage(byte[] image,Context context)
        {
            Repository.updateUserProfile(provideUserLocal(context),image);
        }

        public Realm_User provideUserLocal(Context context)
        {

            String [] params=SaveSharedPreference.getLoggedParams(context);

            Realm_User user=Repository.provideUserLocal(params[0],params[1]);

            return  user;
        }



        public void  updateUserLocalWithRemoteUser(RemoteUser rUser,Realm_User user)
        {

            Repository.updateUserLocalWithRemoteUser(rUser,user);

        }






    public void  updateUserLocalF_Name(Realm_User user, String  fname)
    {

        Repository.updateUserLocalF_Name(user,fname);

    }

    public void  updateUserLocalL_Name(Realm_User user, String  lname)
    {

        Repository.updateUserLocalL_Name(user,lname);

    }

    public void  updateUserLocalPass(Realm_User user, String  pass, Context context)
    {

        Repository.updateUserLocalPass(user,pass);

        String [] params=SaveSharedPreference.getLoggedParams(context);

        SaveSharedPreference.setLoggedIn(context,true,params[0],pass);


    }

    public void  updateUserLocalWeight(Realm_User user, int  weight)
    {

        Repository.updateUserLocalWeight(user,weight);

    }

    public void  updateUserLocalHeight(Realm_User user, int  height)
    {

        Repository.updateUserLocalHeight(user,height);

    }


    public void  updateUserLocalGender(Realm_User user, String  gender)
    {

        Repository.updateUserLocalGender(user,gender);

    }


    public void  updateUserLocalDOB(Realm_User user, String  dob)
    {

        Repository.updateUserLocalDOB(user,dob);

    }


}
