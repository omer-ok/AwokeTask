package com.kampen.riksSe.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.utils.Constants;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContract.View{



    private EditText mUserEmail,mUserPhoneNo;

    private TextView sendEmail;

    String tempUserEmail;

    ForgotPasswordPresenter forgotPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordPresenter =new ForgotPasswordPresenter(ForgotPasswordActivity.this);

        mUserEmail=findViewById(R.id.editText_email);
       // mUserPhoneNo =findViewById(R.id.editText_phone);
        sendEmail =findViewById(R.id.button_email);

        sendEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (validateData()) {

                    mUserEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);

                    tempUserEmail = mUserEmail.getText().toString().trim();


                    try {

                        forgotPasswordPresenter.performForgotPassword(tempUserEmail);
                    } catch (NullPointerException ex) {
                        ex.toString();
                    }




                }
            }
        });

        //setUpDB();

    }


    private boolean validateData() {
        if (mUserEmail.getText().toString().trim().length() == 0) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            return false;

        }

        if (!Constants.isValidEmailId(mUserEmail.getText().toString())) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            return false;
        }

        return true;
    }



    public String GenerateCode(){

        String val = ""+((int)(Math.random()*9000)+1000);
        return val;
    }


    public void sendSMS(String phoneNo, String msg) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void onForgotPassClick(View view) {




    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onBackClick(View view) {

        onBackPressed();
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();


        mUserEmail=null;
    }

    @Override
    public void setForgotPassword(String message,String OTP) {


        moveNext(EmailVerificationActivity.class,OTP,tempUserEmail);



    }

    @Override
    public void setForgotPasswordFailed(String message) {

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.General_NoInternetConnection));
        }
        else if(message.equals("Bad Request")){
            MyApplication.showSimpleSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.LoginModule_CredentialDontMatch));
        }
        else{
            MyApplication.showSimpleSnackBar(ForgotPasswordActivity.this, message);
        }
    }



    @Override
    public void setPresenter(ForgotPasswordContract.Presenter mPresenter) {

    }

    public void moveNext(Class value,String otp,String email) {
        Intent intent = new Intent(getApplicationContext(), value);
        intent.putExtra("OTP",otp);
        intent.putExtra("Email",email);
        startActivity(intent);
        finish();
    }
}
