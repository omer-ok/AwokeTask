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
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.plans.SelectPlansActivity;
import com.kampen.riksSe.payment.PayEx.PayExPaymentActivity;
import com.kampen.riksSe.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;


public class SignUpDuoActivity extends AppCompatActivity {




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

    String Payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_duo);

        Payment =getIntent().getStringExtra("PaymentResponce");

        initViews();


    }



    private void  initViews()
    {
        mUserFname=findViewById(R.id.editText_fName);
        mUserLname=findViewById(R.id.editText_lName);
        mUserEmail=findViewById(R.id.editText_email);
       /* mUserPass=findViewById(R.id.editText_pass);
        mUserPassC=findViewById(R.id.editText_pass_c);
        mUserDOB=findViewById(R.id.editText_Age);
        mUserWeight=findViewById(R.id.editText_Weight);*/
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

        /*if(mUserPass.getText().toString().trim().length()==0 || mUserPass.getText().toString().trim().length() < 6 )
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
        }*/


        return  true;
    }




    public void onNextClick(View view) {

        if(validateData( )) {


            final String fName = mUserFname.getText().toString();

            final String lName = mUserLname.getText().toString();

            final String email= mUserEmail.getText().toString();

/*
            final String pass =mUserPass.getText().toString();
*/



          moveNext(PayExPaymentActivity.class,fName,lName,email);

        }
    }






    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), SelectPlansActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSexClick(View view) {

        final EditText gender= (EditText) view;

        try {

            GenderPickerDialog dialog = new GenderPickerDialog(SignUpDuoActivity.this);
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


        Constants.hideSoftKeyboard(view, SignUpDuoActivity.this);

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
        new DatePickerDialog(SignUpDuoActivity.this,android.R.style.Theme_Holo_Light_Dialog, date, 1990, 0, 1).show();


    }




    public void onWeightClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(SignUpDuoActivity.this)
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




    public void moveNext(Class value,String fName,String lName,String email) {
        Intent intent = new Intent(getApplicationContext(), value);
        intent.putExtra("Status","Duo");
        intent.putExtra("FirstNameDuo",fName);
        intent.putExtra("LastNameDuo",lName);
        intent.putExtra("EmailDuo",email);
        intent.putExtra("PaymentResponce",Payment);
        startActivity(intent);
        finish();
    }

}
