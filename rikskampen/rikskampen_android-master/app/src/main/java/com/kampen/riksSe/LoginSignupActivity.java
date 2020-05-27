package com.kampen.riksSe;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.signup.SignUpActivity;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
    }



/*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

    public void onLoginClick(View view) {


        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onJoinClick(View view) {


        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
        finish();

    }
}
