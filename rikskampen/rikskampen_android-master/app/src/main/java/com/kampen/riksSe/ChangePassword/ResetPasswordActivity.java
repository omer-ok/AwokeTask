package com.kampen.riksSe.ChangePassword;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.signup.SignupPresenter;


public class ResetPasswordActivity extends AppCompatActivity implements ChangePasswordContract.View {


    private EditText Password,RePassword;

    private Button verifyBtn;

    private SignupPresenter mSignupPresenter;

    ChangePasswordPresenter changePasswordPresenter;

    String Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        Bundle bundle = getIntent().getExtras();
        String Email = bundle.getString("Email");
        Status = bundle.getString("Status");
        changePasswordPresenter = new ChangePasswordPresenter(ResetPasswordActivity.this);



        Password=findViewById(R.id.newpassword);

        RePassword =findViewById(R.id.retrypass);

        verifyBtn =  findViewById(R.id.SubmitPassLayout);



        verifyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RePassword.onEditorAction(EditorInfo.IME_ACTION_DONE);


                if (validateData()) {

                    String tempUserPass = Password.getText().toString().trim();
                    String tempUserCPass = RePassword.getText().toString().trim();
                    try {
                        if(tempUserPass.equals(tempUserCPass)){

                            changePasswordPresenter.performChangePasword(Email,tempUserPass);
                        }
                        else{
                            MyApplication.showSimpleSnackBar(ResetPasswordActivity.this,getResources().getString(R.string.LoginModule_EnterValidPassword));
                        }

                    } catch (NullPointerException ex) {
                        ex.toString();
                    }

                }



            }
        });


    }

    private boolean validateData() {


        if(Password.getText().toString().trim().length()==0 )
        {
            Password.requestFocus();
            Password.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            return false;
        }

        if(Password.getText().toString().trim().length() < 6){
            Password.requestFocus();
            Password.setError(getResources().getString(R.string.ResetPasswordModule_InvalidPassword));
            return false;
        }

        if(RePassword.getText().toString().trim().length()==0 )
        {
            RePassword.requestFocus();
            RePassword.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            return false;
        }

        if(RePassword.getText().toString().trim().length() < 6){
            Password.requestFocus();
            Password.setError(getResources().getString(R.string.ResetPasswordModule_InvalidPassword));
            return false;
        }

        return true;
    }


    public void moveNext(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);
        startActivity(intent);
        finish();
    }



    public void onBackClick(View view) {
        if(Status.equals("InAPP")){
            finish();
        }else {
            moveNext(LoginActivity.class);
        }

    }


    @Override
    public void onBackPressed() {
        if(Status.equals("InAPP")){
            finish();
        }else {
            moveNext(LoginActivity.class);
        }

    }



    @Override
    public void setChangePaswordSucess(String message) {

        MyApplication.showSimpleSnackBarSucess(ResetPasswordActivity.this,message);
        //Log.i("ChangePasword S",message);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if(Status.equals("InAPP")){
                    finish();
                }
                else{
                    moveNext(LoginActivity.class);
                }
            }
        }, 1000);


    }

    @Override
    public void setChangePasswordFailed(String message) {

        MyApplication.showSimpleSnackBar(ResetPasswordActivity.this,message);
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter mPresenter) {

    }
}
