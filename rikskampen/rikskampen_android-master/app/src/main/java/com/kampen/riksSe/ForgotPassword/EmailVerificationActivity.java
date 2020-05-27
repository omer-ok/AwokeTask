package com.kampen.riksSe.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kampen.riksSe.ChangePassword.ResetPasswordActivity;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.signup.SignupPresenter;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;


public class EmailVerificationActivity extends AppCompatActivity implements ForgotPasswordContract.View{


    private EditText verifyCode1,verifyCode2,verifyCode3,verifyCode4;

    private LinearLayout verifyBtn;

    private View ReSend;

    String CodeApp;
    private OtpView otpView;

    private SignupPresenter mSignupPresenter;
    String code1;
    String code2;
    String code3;
    String code4;
    ForgotPasswordPresenter forgotPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_code_verification);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Bundle bundle = getIntent().getExtras();
        CodeApp = bundle.getString("OTP");
        String Email = bundle.getString("Email");

        forgotPasswordPresenter =new ForgotPasswordPresenter(EmailVerificationActivity.this);

        ReSend = findViewById(R.id.resendcode);
        otpView = findViewById(R.id.otp_view);
        /*verifyCode1=findViewById(R.id.VerifyCode1);
        verifyCode2=findViewById(R.id.VerifyCode2);
        verifyCode3=findViewById(R.id.VerifyCode3);
        verifyCode4=findViewById(R.id.VerifyCode4);*/

        //verifyBtn = (LinearLayout) findViewById(R.id.VerifyLayout) ;


        /*String code1 = verifyCode1.getText().toString();
        String code2 = verifyCode2.getText().toString();
        String code3 = verifyCode3.getText().toString();
        String code4 = verifyCode4.getText().toString();
        String Code = code1.concat(code2).concat(code3).concat(code4);*/

        ReSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    try {
                        forgotPasswordPresenter.performForgotPassword(Email);
                    } catch (NullPointerException ex) {
                        ex.toString();
                    }

            }
        });


        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                if(otp.equals(CodeApp)){

                   // MyApplication.showSimpleSnackBarSucess(EmailVerificationActivity.this,getResources().getString(R.string.ResetPasswordModule_VerifyCodeSucessfull));
                    Toast.makeText(EmailVerificationActivity.this, getResources().getString(R.string.VerifyCodeModule_CodeVerified), Toast.LENGTH_SHORT).show();
                    moveNext(ResetPasswordActivity.class,Email);
                }
                else{
                    otpView.setText("");
                    //MyApplication.showSimpleSnackBar(EmailVerificationActivity.this,"Code does not Match.");
                    Toast.makeText(EmailVerificationActivity.this, getResources().getString(R.string.VerifyCodeModule_CodeIsInvalid), Toast.LENGTH_SHORT).show();
                }
            }
        });




       /* verifyCode4.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();

                // Perform computations using this string
                // For example: parse the value to an Integer and use this value
                // Set the computed value to the other EditText

                String code1 = verifyCode1.getText().toString();
                String code2 = verifyCode2.getText().toString();
                String code3 = verifyCode3.getText().toString();
                String code4 = verifyCode4.getText().toString();
                String Code = code1.concat(code2).concat(code3).concat(code4);
                Log.i("Code",Code);
                //moveNext(ResetPasswordActivity.class);

                if(Code.equals(CodeApp)){


                    moveNext(ResetPasswordActivity.class,Email);

                    Toast.makeText(EmailVerificationActivity.this, "Code Match SucessFull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EmailVerificationActivity.this, "Code does not Match.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

            }

        });*/



 /*       verifyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                Log.i("Code",Code);

                moveNext(ResetPasswordActivity.class);

            *//*    String UserCode = verifyCode.getText().toString();
                if(UserCode.equals(Code)){

                    moveNext(Lo.class);

                }
                else{
                    Toast.makeText(EmailVerificationActivity.this, "Code does not Match.", Toast.LENGTH_SHORT).show();
                }*//*

            }
        });*/


    }



    public void moveNext(Class value,String email) {
        Intent intent = new Intent(getApplicationContext(), value);
        intent.putExtra("Email",email);
        intent.putExtra("Status"," ");
        startActivity(intent);
        finish();
    }

    public void moveNext1(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);
        startActivity(intent);
        finish();
    }





    public void onBackClick(View view) {
        moveNext1(ForgotPasswordActivity.class);
    }

    @Override
    public void setForgotPassword(String message, String OTP) {

        CodeApp=OTP;

        MyApplication.showSimpleSnackBarSucess(EmailVerificationActivity.this, getResources().getString(R.string.ResetPasswordModule_Description));
    }

    @Override
    public void setForgotPasswordFailed(String message) {

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(EmailVerificationActivity.this, getResources().getString(R.string.General_NoInternetConnection));
        }
        else if(message.equals("Bad Request")){
            MyApplication.showSimpleSnackBar(EmailVerificationActivity.this,  getResources().getString(R.string.LoginModule_CredentialDontMatch));
        }
        else{
            MyApplication.showSimpleSnackBar(EmailVerificationActivity.this, message);
        }
    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter mPresenter) {

    }
}
