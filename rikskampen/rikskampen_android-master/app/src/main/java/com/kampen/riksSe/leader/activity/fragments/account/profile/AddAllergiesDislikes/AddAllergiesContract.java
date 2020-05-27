package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes;

import android.content.Context;
import android.content.res.Configuration;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyResult;

import java.util.ArrayList;
import java.util.List;

public class AddAllergiesContract {


    interface View extends BaseView<Presenter> {



        void setUserAddedAllergiesSucess(String message);
        void setUserAddedAllergiesFailed(String message);

        void setAddAllergiesServerSucess(String message);
        void setAddAllergiesServerFailed(String message);



        void setSearchServerforAllergisSucess(String message, List<AllergyResult> AllergyName);
        void setSearchServerforAllergisFailed(String message);

        void setSyncSucessAPI(String message,String NutritionPDf);
        void setSyncFailAPI(String message);
    }

    interface Presenter extends BasePresenter {
        void syncApiV3(Context context);
       void searchServerforAllergis(Context context,String Keyword);
       void getAddAllergiesAllData(Context context);
       void addAllergiesServer(Context context,ArrayList<String> allergyID,ArrayList<String> other_allergy_name,ArrayList<String> deleted_allergy_id,ArrayList<String> other_deleted_allergy_id);
    }

}
