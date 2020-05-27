package com.kampen.riksSe.leader.activity.fragments.account.editprofile;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class EditPofileSimpleActivity extends AppCompatActivity implements   EditProfileContract.View{



    private EditText mUserDOB;
    private  EditText mUserHeight;

    private  EditText mUserWeight;
    private  EditText mUserGoalWeight;
    private  EditText mUserWaist;
    private EditText mUserGender;

    private  EditText mfNameValue;

    private  EditText mlNameValue;

    private  EditText mPassValue;

    private  EditText mEmailValue;

    private  EditText mCVCValue;

    private Boolean EditProfileStatus=false;


    private ImagePicker imagePicker;

    private ImageView mProfileImage;


    private   File mPictureFile;
    private   File mPictureFileNew;

    private static final int CAMERA_REQUEST = 1888;

    private Realm_User mUser;

    private RemoteUser mRUser;

    public DatePicker picker;
    String ActivityStatus;

    private   EditProfilePresenter  mEditProfilePresenter;


    private Context mContext;

    SwipeRefreshLayout refresProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pofile_simple);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_actionbar);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.START
                *//*| Gravity.CENTER_VERTICAL*//*);
        layoutParams.rightMargin = 0;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);*/
       // actionBar.setBackgroundDrawable(R.drawable.app_bar_color);

     //   ActivityStatus = getIntent().getStringExtra("ActivityStatus");

        init();





        try {
            imagePicker = new ImagePicker();
        }catch (Exception ex)
        {
            ex.toString();
        }







       mEditProfilePresenter=new EditProfilePresenter(EditPofileSimpleActivity.this);



        refresProfile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ProgressManager.showProgress(EditPofileSimpleActivity.this, "Vänta ...");

                String [] params= SaveSharedPreference.getLoggedParams(EditPofileSimpleActivity.this);

                Realm_User user= Repository.provideUserLocal(params[0],params[1]);

                String  token="bearer "+user.getToken();

                mEditProfilePresenter.updateProfile(mUser.getId(),token);



            }
        });



        setUser();

        setEditTextHintSize();


    /*    if(ActivityStatus.equals("BMI")) {
            double height = mUser.getHeight_in_cm();
            double weight = mUser.getWeight();
            String gender = mUser.getUser_gender();
            if (height <= 0) {
                mUserHeight.setError("Height missing ");
                mUserHeight.requestFocus();
            }else if(weight <= 0 ){
                mUserWeight.setError("Weight missing ");
                mUserWeight.requestFocus();
            }else if(gender.equals(null)){
                mUserGender.setError("Gender missing ");
            }

        }*/

    }



    private void setEditTextHintSize(){

        mfNameValue.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible

                    hint = true;
                    mfNameValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mfNameValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "hintFont.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mfNameValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                   /*mfNameValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf")); */// setting the font
                }

                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mlNameValue.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mlNameValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mlNameValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "hintFont.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mlNameValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mlNameValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mEmailValue.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mEmailValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mEmailValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUserGender.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserGender.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserGender.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUserDOB.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserDOB.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserDOB.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUserHeight.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserHeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserHeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUserWeight.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        mUserGoalWeight.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserGoalWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserGoalWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUserWaist.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) {
                    // no text, hint is visible
                    hint = true;
                    mUserWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "m.ttf"));*/ // setting the font
                } else if(hint) {
                    // no hint, text is visible
                    hint = false;
                    mUserWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    /*mEmailValue.setTypeface(Typeface.createFromAsset(getAssets(),
                            "textFont.ttf"));*/ // setting the font
                }
                if(s.length()>0){
                    EditProfileStatus=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    private  void setUser()
    {


        mRUser=new RemoteUser();

         mUser=mEditProfilePresenter.provideUserLocal(mContext);


        if(mUser!=null)
        {
            if(mUser!=null )
            {



                Constants.setProfileImage(mUser.getProfile_image(),mUser.getProfilePicData(),mProfileImage,mContext);

                /*
                GlideApp
                        .with(mContext)
                        .load(mUser.getProfile_image())
                        .into(mProfileImage);
                        */


                final String    dob  =mUser.getDob();

//              final String cvc = mUser.getCvc();

                final double height_ft = mUser.getHeight_in_cm();

                final double weight =mUser.getWeight();
                final double GoalWeight =mUser.getGoalweight();
                final double waist = mUser.getWaist();
                final String gender=mUser.getUser_gender();
                final String passWord = mUser.getPass();


                mfNameValue.setText(mUser.getF_name());

                mlNameValue.setText(mUser.getL_name());

                mEmailValue.setText(mUser.getEmail());



                //mCVCValue.setText( mUser.getCvc());

                mUserDOB.setText(dob);


                if(height_ft>0)
                mUserHeight.setText(height_ft+"");

                if(weight>0)
                mUserWeight.setText(weight+"");

                if(GoalWeight>0)
                    mUserGoalWeight.setText(GoalWeight+"");

                if(waist>0)
                    mUserWaist.setText(waist+"");

                mUserGender.setText(gender);

                /*if(height_ft>0){
                    double stepLenght = (height_ft * 0.415);
                }*/


                //mPassValue.setText(mUser.getPass());


            }

        }
    }


    public File profilepictureDB(Bitmap Image){



        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

        mPictureFile=pictureFile;

        Bitmap tempBitmap=Image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);


        byte[] photo = baos.toByteArray();


        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(photo);
            fos.close();
        } catch (FileNotFoundException e) {
            // Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {

        }


        return pictureFile;
    }

    public void onUpdateClick(View view) {

        String fname = mfNameValue.getText().toString();
        String lname = mlNameValue.getText().toString();
        String pass = mUser.getPass();
        String gender = mUserGender.getText().toString();
        String dob = mUserDOB.getText().toString();
        String CVC = mCVCValue.getText().toString();

        if(fname==null || fname.length()==0)
        {


            mfNameValue.setError(getResources().getString(R.string.UpdateProfile_FirstName_Valid));

            return;
        }
     /*   if(lname==null || lname.length()==0)
        {


            mlNameValue.setError(getResources().getString(R.string.UpdateProfile_LastName_Valid));

            return;
        }*/
        if(gender==null || gender.length()==0)
        {


            mUserGender.setError(getResources().getString(R.string.UpdateProfile_EnterValidGender));

            return;
        }

        if(dob==null || dob.length()==0)
        {
            dob=Constants.DefaultDate;
            mUserDOB.setError(getResources().getString(R.string.UpdateProfile_EnterValidDOB));
            mUserDOB.requestFocus();
            return;
        }



        double HeightInt=0;
        String hight = "0";



            //hight = mUserHeight.getText().toString().replace("(Cm)","").trim();
            hight = mUserHeight.getText().toString().trim();

        if(hight.equals("")){

            hight="0";
        }
        HeightInt = Double.parseDouble(hight);

        if(HeightInt == 0|| HeightInt < 95 || HeightInt > 250)
        {
            hight="0";

            mUserHeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidHeight));
            mUserHeight.requestFocus();
            return;
        }


        String weight ="";
        double WeightInt =0;



            //weight = mUserWeight.getText().toString().replace("(Kg)","").trim();
            weight = mUserWeight.getText().toString().trim();

            if(weight.equals("")){

                weight="0";
            }
              WeightInt = Double.parseDouble(weight);

            if (WeightInt == 0 || WeightInt < 20 || WeightInt > 200) {

                weight = "0";

                mUserWeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidWeight));
                mUserWeight.requestFocus();

                return;
            }

        String Goalweight ="";
        double GoalWeightInt =0;



        //weight = mUserWeight.getText().toString().replace("(Kg)","").trim();
        Goalweight = mUserGoalWeight.getText().toString().trim();

        if(Goalweight.equals("")){

            Goalweight="0";
        }
        GoalWeightInt = Double.parseDouble(Goalweight);

        if (GoalWeightInt == 0 || GoalWeightInt < 20 || GoalWeightInt > 200) {

            weight = "0";

            mUserGoalWeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidGoalWeight));
            mUserGoalWeight.requestFocus();

            return;
        }

        String waist ="";
        double WaisInt =0;



        //weight = mUserWeight.getText().toString().replace("(Kg)","").trim();
        waist = mUserWaist.getText().toString().trim();

        if(waist.equals("")){

            waist="0";
        }
        WaisInt = Double.parseDouble(waist);

        if (WaisInt == 0 || WaisInt < 20 || WaisInt > 200) {

            weight = "0";

            mUserWaist.setError(getResources().getString(R.string.UpdateProfile_EnterValidWaist));
            mUserWaist.requestFocus();

            return;
        }

        if(CVC==null || CVC.length()==0)
        {

            CVC="";


        }


        try {

            if(EditProfileStatus){
                if(mPictureFileNew !=null){
                    ProgressManager.showProgress(EditPofileSimpleActivity.this,getResources().getString(R.string.progress_dialog_message));
                    mEditProfilePresenter.updateUserProfileLocal(mUser,mPictureFileNew,fname,lname,pass,CVC,gender,dob,hight,weight,waist,Goalweight,EditPofileSimpleActivity.this);

                }else{

                /*BitmapDrawable drawable = (BitmapDrawable) mProfileImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Uri tempUri = getImageUriFromInternalPermission(EditPofileSimpleActivity.this, bitmap);
                mPictureFileNew = convertUriIntoFile(tempUri);*/

                    ProgressManager.showProgress(EditPofileSimpleActivity.this,getResources().getString(R.string.progress_dialog_message));
                    mEditProfilePresenter.updateUserProfileLocalWithOutImage(mUser,fname,lname,gender,dob,hight,weight,waist,Goalweight,EditPofileSimpleActivity.this);
                }
            }


        }catch (Exception e){

        }


    }


    @Override
    public void onBackPressed() {

        finish();
    }


    public void onEditProfileClick(View view) {


        //isReadStoragePermissionGranted();

        //startPickingImage();
        cameraPErmissionGranted();


    }

    private  void init()
    {

        mContext=EditPofileSimpleActivity.this;

        refresProfile =findViewById(R.id.pullToRefresh);

        mProfileImage=findViewById(R.id.profileImage);

        mUserGender=findViewById(R.id.genderValue);

        mUserDOB=findViewById(R.id.dobValuetxt);

        mUserHeight=findViewById(R.id.heightValue);

        mUserWeight=findViewById(R.id.weightValue);

        mUserGoalWeight=findViewById(R.id.goalWeightValue);

        mUserWaist=findViewById(R.id.waistValue);

        mfNameValue=findViewById(R.id.fNameValue);

        mlNameValue=findViewById(R.id.lNameValue);

        mEmailValue=findViewById(R.id.editText_email);

        mPassValue=findViewById(R.id.passValue);

        mCVCValue=findViewById(R.id.NICValue);

    }






    public  void  startPickingImage()
    {
        try {
           /* imagePicker.withActivity(this) //calling from activity
                    //.withFragment(W) //calling from fragment
                    .chooseFromGallery(false) //default is true
                    .chooseFromCamera(true) //default is true
                    .withCompression(true) //default is true

                    .start();*/


            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

        }catch (Exception ex)
        {
            ex.toString();
        }
    }


    public boolean cameraPErmissionGranted(){

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                /*&& ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED*/) {

                startPickingImage();

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,/*Manifest.permission.RECORD_AUDIO*/}, 3);
                return false;

            }
        }
        else { //permission is automatically granted on sdk<23 upon installation

            startPickingImage();

            return true;
        }
    }


    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(EditPofileSimpleActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                isWriteStoragePermissionGranted();

                return true;
            } else {


                ActivityCompat.requestPermissions(EditPofileSimpleActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation


            startPickingImage();

            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(EditPofileSimpleActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED      && ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    /*&& ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED*/) {

                startPickingImage();

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,/*Manifest.permission.RECORD_AUDIO*/}, 3);
                return false;

            }
        }
        else { //permission is automatically granted on sdk<23 upon installation

            startPickingImage();

            return true;
        }
    }




    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:

                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    /*isWriteStoragePermissionGranted();*/
                    startPickingImage();

                }else{


                }
                break;

            case 3:


                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    try {
                        /*imagePicker.withActivity(EditPofileSimpleActivity.this) //calling from activity

                                .chooseFromGallery(false) //default is true
                                .chooseFromCamera(true) //default is true
                                .withCompression(true) //default is true
                                .start();*/
                        startPickingImage();
                    }catch (Exception ex)
                    {
                        ex.toString();
                    }

                }else{

                }
                break;
        }
    }
    /*
     * TO COMPRESS THE BITMAP
     * TO STORE IN TEMPORARY PATH WITH INTERNAL PERMISSION
     * */
    public Uri getImageUriFromInternalPermission(Context inContext, Bitmap inImage) {
        ContextWrapper wrapper = new ContextWrapper(inContext);
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(getExternalFilesDir(null), "pic.jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            inImage.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI;

    }

    /*
     * TO STORE FILE IN REAL|PERMANENT PATH
     * e.g. /storage/emulated/0/Pictures/FILENAME.EXTENSION
     * */
    public File convertUriIntoFile(Uri uri) {
        File filePath = null;
        if (uri != null) {
            filePath =  new File(String.valueOf(uri));
        }
        return filePath;
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mProfileImage.setImageBitmap(bitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUriFromInternalPermission(EditPofileSimpleActivity.this, bitmap);
            EditProfileStatus = true;
            mPictureFileNew = convertUriIntoFile(tempUri);

            //mEditProfilePresenter.setUserProfileImage( Constants.BitmapToBytes(bitmap),EditPofileSimpleActivity.this);


        }

    }



    private static int MEDIA_TYPE_IMAGE=1;

   /* public void onActivityResult(int requestCode, final int resultCode, Intent data) {



        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == ActivityDaily.RESULT_OK) {

            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {

                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath,bmOptions);

                   //Bitmap selectedImage = BitmapFactory.decodeFile(filePath);


                    mProfileImage.setImageBitmap(selectedImage);

                    File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

                    mPictureFile=pictureFile;

                    Bitmap tempBitmap=selectedImage;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);


                    byte[] photo = baos.toByteArray();

                    if (pictureFile == null){

                        return;
                    }

                    try {
                        FileOutputStream fos = new FileOutputStream(pictureFile);
                        fos.write(photo);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        // Log.d(TAG, "File not found: " + e.getMessage());
                    } catch (IOException e) {

                    }

                    mEditProfilePresenter.setUserProfileImage( Constants.BitmapToBytes(selectedImage),EditPofileSimpleActivity.this);



                }
            });
        }

        try {
            String filePath = imagePicker.getImageFilePath(data);
            if (filePath != null) {

            }
        }catch (Exception ex)
        {
            ex.toString();

        }

        super.onActivityResult(requestCode, resultCode, data);




    }

*/

    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Rikskampen");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    private static File getOutputMediaFile(int type,Bitmap bitmap){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Rikskampen");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void onGenderClick(View view) {



        onSexClick();

    }

    public void onSexClick() {





        final CharSequence[] items = {
                getResources().getString(R.string.UpdateProfile_Male), getResources().getString(R.string.UpdateProfile_Female)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.UpdateProfile_Choose));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                mUserGender.setText(items[item]);

                mEditProfilePresenter.updateUserLocalGender(mUser,items[item].toString());

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void onDOBClick(View view) {


        final Calendar myCalendar = Calendar.getInstance();

        if(mUser.getDob()!=null && mUser.getDob().trim().length()>0) {


            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
            Date date = new Date();
            try {
                date = dateFormat.parse(mUser.getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            myCalendar.setTime(date);
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
            Date date = new Date();
            try {//dd-MM-yyyy
                date = dateFormat.parse("1990-01-01");

            } catch (ParseException e) {
                e.printStackTrace();
            }

            myCalendar.setTime(date);
        }

        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year );
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());

                mUserDOB.setText(sdf.format(myCalendar.getTime()));




                mEditProfilePresenter.updateUserLocalDOB(mUser,sdf.format(myCalendar.getTime()));
                mUser=mEditProfilePresenter.provideUserLocal(mContext);

            }

        };
        //new DatePickerDialog(EditPofileSimpleActivity.this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        DatePickerDialog dialog =new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog ,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Date d=new Date();
        Calendar cal=Calendar.getInstance();
        cal.set(2006, 1, 1, 0, 0);
        d.setTime(cal.getTimeInMillis());


        Date d2=new Date();
        Calendar cal2=Calendar.getInstance();
        cal2.set(1950, 1, 1, 0, 0);
        d2.setTime(cal2.getTimeInMillis());

        dialog.getDatePicker().setMaxDate(d.getTime());
        dialog.getDatePicker().setMinDate(d2.getTime());
        dialog.show();

       /* mUserDOB.setVisibility(View.GONE);
        picker.setVisibility(View.VISIBLE);*/



    }


  /*  public void onHeightClick(View view) {


        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        userInput.setInputType(InputType.TYPE_CLASS_NUMBER);


        userInput.setText(mUser.getHeight_in_cm()+"");

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {


                                mUserHeight.setText(userInput.getText()+" cm");

                                mEditProfilePresenter.updateUserLocalHeight(mUser,Integer.parseInt(userInput.getText().toString()));

                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }*/

    public void onBackClick(View view) {

        if(EditProfileStatus){
            DialogeBoxSaveEditProfile();
        }else{
            finish();
        }
        //

    }

    public void onFNameClick(View view)
    {



    }



    public   void updateUserToServer(RemoteUser user)
    {
        ProgressManager.showProgress(EditPofileSimpleActivity.this,"Updating user");

        mEditProfilePresenter.performEditProfile(user.getFirstname(),user.getLastname(),user.getPassword(),user.getEmail());
    }

/*

    public void onLNameClick(View view) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);

        userInput.setText(mUser.getL_name());

        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        titleInput.setText("Enter your last name");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mlNameValue.setText(userInput.getText());

                                mRUser=Constants.DB_To_R_USER(mUser);
                                mRUser.setLastname(userInput.getText().toString());
                                updateUserToServer(mRUser);
                                mEditProfilePresenter.updateUserLocalL_Name(mUser,userInput.getText().toString());


                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();


    }
*/





/*    public void onPassClick(View view) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.pass_input_prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);

        final EditText userInputTwo = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInputTwo);



        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        titleInput.setText("Change your password");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {


                                if(!userInput.getText().toString().equals(mUser.getPass()))
                                {

                                    Toast.makeText(mContext, "Password doesn't match", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                else if(userInputTwo.getText().toString().trim().length()==0)
                                {

                                    Toast.makeText(mContext, "Enter new password", Toast.LENGTH_SHORT).show();
                                    return;
                                }



                                mPassValue.setText(userInputTwo.getText());

                                mEditProfilePresenter.updateUserLocalPass(mUser,userInputTwo.getText().toString(),mContext);

                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();


    }*/


    @Override
    public void setEditProfile(String message) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPictureFileNew= null;
                ProgressManager.hideProgress();
                finish();
            }
        }, 1000);
        EditProfileStatus=false;


        //MyApplication.showSimpleSnackBarSucess(mContext, message);
        /*if(ActivityStatus.equals("BMI")) {
            Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString(NAVIGATING_FROM_TAG,"abc");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else {
            finish();
        }
     */
        /*Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,"abc");
        intent.putExtras(bundle);
        startActivity(intent);
        finish();*/
        //finish();

    }

    @Override
    public void setEditProfileFailed(String message) {

        mPictureFileNew= null;
        ProgressManager.hideProgress();
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        //MyApplication.showSimpleSnackBar(mContext, message);

        if(message.equals("Unauthorized")){

            SaveSharedPreference.setUsertodaySteps(EditPofileSimpleActivity.this,0);
            SaveSharedPreference.setSensorSteps(EditPofileSimpleActivity.this,0);
            LogOutUnautorizedUser();
        }
    }

    @Override
    public void UpdateEditProfileSucess(String message) {

        ProgressManager.hideProgress();
        refresProfile.setRefreshing(false);
        setUser();
        MyApplication.showSimpleSnackBarSucess(mContext, message);
        EditProfileStatus=false;
    }

    @Override
    public void UpdateEditProfileFailed(String message) {
        ProgressManager.hideProgress();
        refresProfile.setRefreshing(false);
        MyApplication.showSimpleSnackBar(mContext, message);
        if(message.equals("Unauthorized")){

            SaveSharedPreference.setUsertodaySteps(EditPofileSimpleActivity.this,0);
            SaveSharedPreference.setSensorSteps(EditPofileSimpleActivity.this,0);
            LogOutUnautorizedUser();
        }
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter mPresenter) {



    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    private void LogOutUnautorizedUser(){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mLocalService.deleteAll();

            }
        });

        Toast.makeText(mContext,  getResources().getString(R.string.LoginModule_Session_Timed_Out), Toast.LENGTH_SHORT).show();
        if (Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
            ActivityFragment.newInstance().stopGPSService(mContext);
        }
        //MyApplication.showSimpleSnackBar(EditPofileSimpleActivity.this, "Your Logged in Some where else");
        Intent intent = new Intent(EditPofileSimpleActivity.this, LoginActivity.class);
        startActivity(intent);

        SaveSharedPreference.setLoggedIn(EditPofileSimpleActivity.this, false);

        finish();

        /*LayoutInflater li = LayoutInflater.from(EditPofileSimpleActivity.this);

        View promptsView = li.inflate(R.layout.dialog_box_logout, null);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(EditPofileSimpleActivity.this);

        builder.setView(promptsView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        mLocalService.deleteAll();

                    }
                });

                MyApplication.showSimpleSnackBar(EditPofileSimpleActivity.this, "Your Logged in Some where else");
                Intent intent = new Intent(EditPofileSimpleActivity.this, LoginActivity.class);
                startActivity(intent);

                SaveSharedPreference.setLoggedIn(EditPofileSimpleActivity.this, false);

                finish();


            }
        });

        builder.show();*/

    }



    public void DialogeBoxSaveEditProfile(){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.dialog_box_update_profile_activity, null);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);

        builder.setView(promptsView);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        android.app.AlertDialog alertDialog = builder.create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onUpdateClick(v);
                alertDialog.dismiss();

            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

                alertDialog.dismiss();
            }
        });



        alertDialog.show();

    }


}
