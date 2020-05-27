package com.kampen.riksSe.signup;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.LoginSignupActivity;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;

import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import io.realm.Realm;


public class SignUpActivity extends AppCompatActivity implements  SignupContract.View{




    private  EditText mUserFname;
    private  EditText mUserLname;

    private  EditText mUserEmail;
    private  EditText mUserPass;
    private  EditText mUserPassC;
    private  EditText mUserDOB;
    private  EditText mUserWeight;


    private SignupPresenter mSignupPresenter;

    private FirebaseAuth auth;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        initViews();

        mSignupPresenter=new SignupPresenter(SignUpActivity.this);

        //FirebaseDatabase.getInstance();
        //FirebaseApp.initializeApp(SignUpActivity.this);
      // auth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(SignUpActivity.this));
    }



    private void  initViews()
    {
        mUserFname=findViewById(R.id.editText_fName);
        mUserLname=findViewById(R.id.editText_lName);
        mUserEmail=findViewById(R.id.editText_email);
        mUserPass=findViewById(R.id.editText_pass);
        mUserPassC=findViewById(R.id.editText_pass_c);
        mUserDOB=findViewById(R.id.editText_Age);
        mUserWeight=findViewById(R.id.editText_Weight);
    }

    private  boolean  validateData( )
    {

        if(mUserFname.getText().toString().trim().length()==0)
        {
            mUserFname.requestFocus();
            mUserFname.setError("Enter first name");
            return false;

        }


        if(mUserEmail.getText().toString().trim().length()==0)
        {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter email");
            return false;

        }

        if(!Constants.isValidEmailId(mUserEmail.getText().toString()))
        {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter valid email");
            return false;
        }

        if(mUserPass.getText().toString().trim().length()==0 || mUserPass.getText().toString().trim().length() < 6 )
        {
            mUserPass.requestFocus();
            mUserPass.setError("Password must me more than 6 letters");
            return false;

        }

        if(mUserPassC.getText().toString().trim().length()==0 || mUserPassC.getText().toString().trim().length() < 6)
        {
            mUserPassC.requestFocus();
            mUserPassC.setError("Password must me more than 6 letters");
            return false;

        }

        String temp1=mUserPass.getText().toString();
        String temp2=mUserPassC.getText().toString();

        if(!temp1.equals(temp2))
        {
            mUserPass.requestFocus();
            mUserPass.setError("Password do not match");
            return false;
        }


        return  true;
    }




    public void onNextClick(View view) {

        if(validateData( )) {


          /*  Constants.hideSoftKeyboard(view,SignUpActivity.this);

            ProgressManager.showProgress(SignUpActivity.this,"Please Wait...");*/

            final String fName = mUserFname.getText().toString();

            final String lName = mUserLname.getText().toString();

            final String email= mUserEmail.getText().toString();

            final String pass =mUserPass.getText().toString();


         // mSignupPresenter.performSign_up(fName,lName,pass,email);

          moveNext(SignUpActivity2.class,fName,lName,pass,email);

         //   mSignupPresenter.performOFflineSighUp(fName,lName,email,pass);

       /*     if(addUserLocalWithoutAPI(fName,lName,email,pass).equals("Sucess")){

               // addUserLocalWithoutAPI(fName,lName,email,pass);
                moveNext(IntroActivity.class);
            }
*/
           // setSignup("");
        }
    }



    public static String addUserLocalWithoutAPI(String fName, String lName, String Email, String Pass)
    {

        Realm realm= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm.createObject(Realm_User.class);

                /*"token": "OTlrdUZzTTFTV0UyT21JOUREN1lnQU9KQmFFWmN3VXdFVE5zVmtyTQ==",
                        "id": 31,
                        "firstname": "umer",
                        "lastname": "javaid",
                        "email": "you1@gmail.com",
                        "gender": null,
                        "dateOfBirth": null,
                        "streetAddress": null,
                        "phonenumber": null,
                        "profileImage": "",
                        "thumbImage": "",
                        "height": "0.00",
                        "weight": "0.00",
                        "role_id": "3"*/

                // db_user.setToken(token);
                db_user.setF_name(fName);
                db_user.setL_name(lName);
                db_user.setEmail(Email);
                db_user.setPass(Pass);
           /*     db_user.setUser_gender(Gender);
                db_user.setDob(DoB);
                db_user.setStreetAddress(Address);
                db_user.setPhonenumber(PhoneNo);
               // db_user.setProfile_image(userJson.getProfileImage());
                try{
                    db_user.setHeight_in_cm(Integer.parseInt(Height));}catch (Exception ex){}
                try{
                    db_user.setWeight(Integer.parseInt(Weight));}catch (Exception ex){}*/

                //db_user.setId(userJson.getId());




            }
        });

        return "Sucess";
    }



    public String GenerateCode(){

        String val = ""+((int)(Math.random()*9000)+1000);
        return val;
    }

    public void onBackClick(View view) {

        onBackPressed();
    }




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSexClick(View view) {

        final EditText gender= (EditText) view;

        try {

            GenderPickerDialog dialog = new GenderPickerDialog(SignUpActivity.this);
            dialog.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                @Override
                public void onSelectingGender(String value) {
                    gender.setText(value);
                }
            });
            dialog.show();
        }catch (Exception ex)
        {
            ex.toString();
        }

    }


    public void onAgeClick(View view) {


        Constants.hideSoftKeyboard(view,SignUpActivity.this);

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                mUserDOB.setText(sdf.format(myCalendar.getTime()));
            }

        };
        new DatePickerDialog(SignUpActivity.this,android.R.style.Theme_Holo_Light_Dialog, date, 1990, 0, 1).show();


    }




    public void onWeightClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(SignUpActivity.this)
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


        new AlertDialog.Builder(this)
                .setTitle("Weight in kg")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserWeight.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }



    @Override
    public void setSignup(String message) {

        ProgressManager.hideProgress();

        SaveSharedPreference.setLoggedIn(SignUpActivity.this,true,mUserEmail.getText().toString(),mUserPass.getText().toString());


    }

    @Override
    public void setSignupFailed(String message) {

        ProgressManager.hideProgress();

        MyApplication.showSimpleSnackBar(SignUpActivity.this, message);

    }

    public void moveNext(Class value,String fName,String lName,String pass,String email) {
        Intent intent = new Intent(getApplicationContext(), value);
        intent.putExtra("FirstName",fName);
        intent.putExtra("LastName",lName);
        intent.putExtra("Password",pass);
        intent.putExtra("Email",email);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(SignupContract.Presenter mPresenter) {

    }
}
