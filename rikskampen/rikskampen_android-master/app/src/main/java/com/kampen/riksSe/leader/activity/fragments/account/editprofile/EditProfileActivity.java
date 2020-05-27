package com.kampen.riksSe.leader.activity.fragments.account.editprofile;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;


import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;

import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;

public class EditProfileActivity extends AppCompatActivity implements   EditProfileContract.View {



    private  TextView mUserDOB;
    private  TextView mUserHeight;

    private  TextView mUserWeight;
    private TextView mUserGender;

    private  TextView mfNameValue;

    private  TextView mlNameValue;

    private  TextView mPassValue;


    private ImagePicker imagePicker;

    private ImageView  mProfileImage;


    private Realm_User mUser;

    private  RemoteUser mRUser;


    private   EditProfilePresenter  mEditProfilePresenter;


    private Context   mContext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        init();


        imagePicker = new ImagePicker();



        mEditProfilePresenter=new EditProfilePresenter(EditProfileActivity.this);

        setUser();
    }





    private  void setUser()
    {


        mRUser=new RemoteUser();

       mUser=mEditProfilePresenter.provideUserLocal(mContext);





         if(mUser!=null )
            {

                byte []  profileData=mUser.getProfilePicData();

                if(profileData!=null)
                {
                    Bitmap bmp = BitmapFactory.decodeByteArray(profileData, 0, profileData.length);
                    mProfileImage.setImageBitmap(bmp);
                }

                final String    dob  =mUser.getDob();

                final double       height_ft= mUser.getHeight_in_cm();

                final double    weight=mUser.getWeight();

                final String    gender=mUser.getUser_gender();


                mfNameValue.setText(mUser.getF_name());

                mlNameValue.setText(mUser.getL_name());

                mUserDOB.setText(dob);

                mUserHeight.setText(height_ft+" cm");

                mUserWeight.setText(weight+" kg");


                mUserGender.setText(gender);



                mPassValue.setText(mUser.getPass());


            }


    }





    @Override
    public void onBackPressed() {

        finish();
    }


    public void onEditProfileClick(View view) {


        isReadStoragePermissionGranted();


    }

    private  void init()
    {

        mContext=EditProfileActivity.this;

        mProfileImage=findViewById(R.id.profileImage);



        mUserGender=findViewById(R.id.genderValue);
        mUserDOB=findViewById(R.id.dobValue);
        mUserHeight=findViewById(R.id.heightValue);

        mUserWeight=findViewById(R.id.weightValue);

        mfNameValue=findViewById(R.id.fNameValue);

        mlNameValue=findViewById(R.id.lNameValue);

        mPassValue=findViewById(R.id.passValue);


    }




    public void onWeightClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(1)
                .maxValue(200)
                .defaultValue(70)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        if(mUser.getWeight()>0) {

            numberPicker.setValue((int) mUser.getWeight());
        }


        new AlertDialog.Builder(this)
                .setTitle("Weight in kg")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserWeight.setText(numberPicker.getValue()+" kg");

                        mEditProfilePresenter.updateUserLocalWeight(mUser,numberPicker.getValue());

                    }
                })
                .show();

    }



    public  void  startPickingImage()
    {
        try {
            imagePicker.withActivity(EditProfileActivity.this) //calling from activity
                    //.withFragment(W) //calling from fragment
                    .chooseFromGallery(true) //default is true
                    .chooseFromCamera(true) //default is true
                    .withCompression(true) //default is true

                    .start();
        }catch (Exception ex)
        {
            ex.toString();
        }
    }




    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                isWriteStoragePermissionGranted();

                return true;
            } else {


                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
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
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                startPickingImage();

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
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


                }else{


                }
                break;

            case 3:


                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    try {
                        imagePicker.withActivity(EditProfileActivity.this) //calling from activity

                                .chooseFromGallery(true) //default is true
                                .chooseFromCamera(true) //default is true
                                .withCompression(true) //default is true
                                .start();
                    }catch (Exception ex)
                    {
                        ex.toString();
                    }

                }else{

                }
                break;
        }
    }





    public void onActivityResult(int requestCode, final int resultCode, Intent data) {



        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {

            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {



                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

                    mProfileImage.setImageBitmap(selectedImage);

                    mEditProfilePresenter.setUserProfileImage( Constants.BitmapToBytes(selectedImage),EditProfileActivity.this);



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


    public void onGenderClick(View view) {



        onSexClick();

    }

    public void onSexClick() {



       /* try {

            if(mUserGender.getText().toString().toLowerCase().equals("male"))
            {

            }

            GenderPickerDialog dialog = new GenderPickerDialog(this);



            dialog.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                @Override
                public void onSelectingGender(String value) {

                    mUserGender.setText(value);

                    int genderInt=1;

                    if(value.toLowerCase().equals("male"))
                    {
                        genderInt=1;
                    }
                    else
                    {
                        genderInt=2;
                    }





                }
            });
            dialog.show();
        }catch (Exception ex)
        {
            ex.toString();
        }*/


        final CharSequence[] items = {
                "Male", "Female", "Other"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selection your gender");
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
                date = dateFormat.parse("01-01-1990");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            myCalendar.setTime(date);
        }

        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());

                mUserDOB.setText(sdf.format(myCalendar.getTime()));

                mEditProfilePresenter.updateUserLocalDOB(mUser,sdf.format(myCalendar.getTime()));

            }

        };
        new DatePickerDialog(EditProfileActivity.this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }


    public void onHeightClick(View view) {


      /*  LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


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


        alertDialog.show();*/

    }

    public void onBackClick(View view) {

        finish();

    }

    public void onFNameClick(View view) {



      /*  LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);



        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        userInput.setText(mUser.getF_name());


        titleInput.setText("Enter your first name");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mfNameValue.setText(userInput.getText());


                                  mRUser=Constants.DB_To_R_USER(mUser);
                                  mRUser.setFirstname(userInput.getText().toString());
                                  updateUserToServer(mRUser);
                                  mEditProfilePresenter.updateUserLocalF_Name(mUser,userInput.getText().toString());


                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();*/


    }



    public   void updateUserToServer(RemoteUser user)
    {
        ProgressManager.showProgress(EditProfileActivity.this,"Updating user");

        mEditProfilePresenter.performEditProfile(user.getFirstname(),user.getLastname(),user.getPassword(),user.getEmail());
    }


    public void onLNameClick(View view) {

      /*  LayoutInflater li = LayoutInflater.from(this);
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


        alertDialog.show();*/


    }






    public void onPassClick(View view) {

        /*LayoutInflater li = LayoutInflater.from(this);
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
*/

    }

    @Override
    public void setEditProfile(String message) {

       // mEditProfilePresenter.updateUserLocalWithRemoteUser(mRUser,mUser);

        ProgressManager.hideProgress();
        MyApplication.showSimpleSnackBar(mContext, message);

    }

    @Override
    public void setEditProfileFailed(String message) {

        ProgressManager.hideProgress();
        MyApplication.showSimpleSnackBar(mContext, message);

    }

    @Override
    public void UpdateEditProfileSucess(String message) {

    }

    @Override
    public void UpdateEditProfileFailed(String message) {

    }

    @Override
    public void setPresenter(EditProfileContract.Presenter mPresenter) {



    }
}
