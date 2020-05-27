package com.kampen.riksSe.signup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.plans.IntroActivity;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SignUpActivity2 extends AppCompatActivity implements  SignupContract.View{


    private EditText mUserDOB;
    private  EditText mUserHeight;

    private  EditText mUserWeight;
    private EditText mUserGender;

    private Context mContext;

    private SignupPresenter mSignupPresenter;
    String fName;
    String lName;
    String pass;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_2);
        mSignupPresenter=new SignupPresenter(SignUpActivity2.this);

        mContext =SignUpActivity2.this;

        fName = getIntent().getStringExtra("FirstName");
        lName = getIntent().getStringExtra("LastName");
        pass = getIntent().getStringExtra("Password");
        email = getIntent().getStringExtra("Email");

        init();

    }

    public void onNextClick(View view) {


        String gender = mUserGender.getText().toString();
        String dob = mUserDOB.getText().toString();


        if(gender==null || gender.length()==0)
        {
            gender="Male";

            mUserGender.setError("Gender missing");

            return;
        }

        if(dob==null || dob.length()==0)
        {
            dob=Constants.DefaultDate;

            mUserDOB.setError("Date of birth  missing");

            return;
        }

        String hight = mUserHeight.getText().toString();
        int HeightInt = Integer.parseInt(hight);

        if(hight==null || hight.length()==0 || HeightInt < 95 || HeightInt > 250)
        {
            hight="0";

            mUserHeight.setError("Height missing or Invalid Number");
            mUserHeight.requestFocus();
            return;
        }

        String weight = mUserWeight.getText().toString();

        int WeightInt = Integer.parseInt(weight);

        if(weight==null || weight.length()==0 || WeightInt < 20 || WeightInt >200)
        {

            weight="0";

            mUserWeight.setError("Weight missing or Invalid Number");
            mUserWeight.requestFocus();
            return;
        }

     //   moveNext(ForgotPasswordActivity.class);

        mUserWeight.onEditorAction(EditorInfo.IME_ACTION_DONE);

        Constants.hideSoftKeyboard(view,SignUpActivity2.this);

        ProgressManager.showProgress(SignUpActivity2.this,"Please Wait...");

        mSignupPresenter.performSign_up(fName,lName,pass,email,gender,dob,hight,weight);

       // moveNext(IntroActivity.class);


    }

    private  void init()
    {

        mContext=SignUpActivity2.this;






        mUserGender=findViewById(R.id.genderValue);
        mUserDOB=findViewById(R.id.dobValue);
        mUserHeight=findViewById(R.id.heightValue);

        mUserWeight=findViewById(R.id.weightValue);




    }



    public void onGenderClick(View view) {



        onSexClick();

    }

    public void onSexClick() {





        final CharSequence[] items = {
                "Male", "Female", "Other"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selection your gender");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                mUserGender.setText(items[item]);

              //  mEditProfilePresenter.updateUserLocalGender(mUser,items[item].toString());

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }



    public void onDoBClick(View view) {


        final Calendar myCalendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date();
        try {//dd-MM-yyyy
            date = dateFormat.parse("1975-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        myCalendar.setTime(date);



        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year );
                myCalendar.set(Calendar.MONTH, monthOfYear );
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT , Locale.getDefault());


                mUserDOB.setText(sdf.format(myCalendar.getTime()));

               /* mEditProfilePresenter.updateUserLocalDOB(mUser,sdf.format(myCalendar.getTime()));
                mUser=mEditProfilePresenter.provideUserLocal(mContext);*/

            }

        };

      //  new DatePickerDialog(SignUpActivity2.this ,android.R.style.Theme_Holo_Light_Dialog, datePicker , myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


        DatePickerDialog dialog =new DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));


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



    }




    public void moveNext(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);
        startActivity(intent);
        finish();
    }


    @Override
    public void setSignup(String message) {

        ProgressManager.hideProgress();

        SaveSharedPreference.setLoggedIn(SignUpActivity2.this,true,email,pass);
        moveNext(IntroActivity.class);



    }

    @Override
    public void setSignupFailed(String message) {
        ProgressManager.hideProgress();

       // MyApplication.showSimpleSnackBar(SignUpActivity2.this, message);

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(mContext, "No Internet Connection");
        }
        else{
            MyApplication.showSimpleSnackBar(mContext, message);
        }
    }

    @Override
    public void setPresenter(SignupContract.Presenter mPresenter) {

    }

    public void onBackClick(View view) {

        onBackPressed();
    }




    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
