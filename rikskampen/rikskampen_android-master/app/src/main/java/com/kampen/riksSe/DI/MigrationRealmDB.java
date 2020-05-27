package com.kampen.riksSe.DI;

import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;

import java.util.ArrayList;
import java.util.List;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.RealmSchema;

public class MigrationRealmDB implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();


        //Log.i(" Realm Version",oldVersion+"");

        if(oldVersion==9){

            schema.get("Ingredient")
                    //.addPrimaryKey("mId")
                    .addField("mFats_Unit",String.class)
                    .addField("mProtein_Unit",String.class)
                    .addField("mCarbs_Unit",String.class)
                    .addField("mUnit_Sv",String.class)
                    .addField("mFats_Unit_Sv",String.class)
                    .addField("mProtein_Unit_Sv",String.class)
                    .addField("mCarbs_Unit_Sv",String.class);
            oldVersion++;
        }

        if (oldVersion == 10) {


            schema.create("Question")
                    .addField("id",int.class)
                    .addPrimaryKey("id")
                    .addField("question",String.class)
                    .addField("order_id",int.class);

            schema.create("QuestionResponse")
                    .addField("mQuestionId",int.class)
                    .addField("mResponse",boolean.class);


            schema.create("diaries")
                    .addField("mid",Integer.class)
                    .addField("mUserId",int.class)
                    .addField("mDayDescription",String.class)
                    .addField("mDayStatus",Boolean.class)
                    .addRealmListField("mQuestions", schema.get("QuestionResponse"))
                    .addField("mDiary_Date",String.class)
                    .addPrimaryKey("mDiary_Date");

            oldVersion++;
        }

        if(oldVersion==11){

            schema.get("ActivityDaily")
                    .addField("mLatitude",double.class)
                    .addField("mLongitude",double.class);

            oldVersion++;
        }
        if(oldVersion==12){

            schema.get("ActivityDaily")
                    .removePrimaryKey();

            oldVersion++;
        }

        if(oldVersion==13){

            schema.create("AppUpdate")
                    .addField("mId",int.class)
                    .addField("mIsForceUpdate",boolean.class)
                    .addField("mVersion",String.class);

            oldVersion++;
        }
        if(oldVersion==14){
            if(!schema.get("MotivationalVideo").hasPrimaryKey()){
                realm.delete("MotivationalVideo");
                schema.get("MotivationalVideo")
                        .removeField("mId")
                        .addField("mId",int.class, FieldAttribute.PRIMARY_KEY);
            }
            oldVersion++;
        }
        if(oldVersion==15){
            if(!schema.get("MotivationalVideo").hasPrimaryKey()){
                realm.delete("MotivationalVideo");
                schema.get("MotivationalVideo")
                        .removeField("mId")
                        .addField("mId",int.class, FieldAttribute.PRIMARY_KEY);
            }
            oldVersion++;
        }
        if(oldVersion==16){
            schema.get("Competition")
                    .addField("mName",String.class);

            schema.get("LeaderBoardAllDataV3")
                    .addRealmListField("mCompetition", schema.get("Competition"));
            oldVersion++;
        }
        if(oldVersion==17){
            schema.get("Realm_User")
                    .removeField("role");

            schema.get("Realm_User")
                    .addField("userRoleID",int.class)
                    .addField("mDesignation",String.class);

            schema.create("ContestantDB")
                    .addField("id",int.class)
                    .addField("name",String.class);

            schema.create("Contestant")
                    .addField("id",int.class)
                    .addField("name",String.class)
                    .addField("email",String.class)
                    .addField("profileImage",String.class)
                    .addField("photo",String.class);
            schema.create("Trainer")
                    .addField("id",int.class)
                    .addField("name",String.class)
                    .addField("email",String.class)
                    .addField("profileImage",String.class)
                    .addField("photo",String.class);
            schema.create("ScheduledLiveVideoCall")
                    .addField("id",Integer.class)
                    .addField("slotID",Integer.class)
                    .addField("trainerId",Integer.class)
                    .addField("userId",Integer.class)
                    .addField("contestId",Integer.class)
                    .addField("timezone",String.class)
                    .addField("sessionStartsAt",String.class)
                    .addField("sessionEndsAt",String.class)
                    .addField("durationInMinutes",Integer.class)
                    .addField("sessionPreCancellationMinutes",Integer.class)
                    .addField("comment",String.class)
                    .addField("status",String.class)
                    .addField("createdAt",String.class)
                    .addField("updatedAt",String.class)
                    .addRealmObjectField("contestant",schema.get("Contestant"))
                    .addRealmObjectField("trainer",schema.get("Trainer"));

            schema.create("UserRoles")
                    .addField("id",int.class)
                    .addField("name",String.class)
                    .addField("displayName",String.class);
            oldVersion++;
        }

    }
}
