package com.kampen.riksSe.payment.PayEx;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

public class PayExThankYou extends AppCompatActivity implements PaymentSucessContract.View{




    TextView OrderNumber,RefrenceNumber,EmailTxt;
    PaymentSucessPresenter paymentSucessPresenter;

    String Email,fname,lname,password,gender,height,weight,dob,PaymentStatus,userID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payex_thankyou);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
      //  getSupportActionBar().setHomeAsUpIndicator(upArrow);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_actionbar);
        androidx.appcompat.app.ActionBar.LayoutParams layoutParams = new androidx.appcompat.app.ActionBar.LayoutParams(
                androidx.appcompat.app.ActionBar.LayoutParams.WRAP_CONTENT,
                androidx.appcompat.app.ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL
                /*| Gravity.CENTER_VERTICAL*/);
        layoutParams.rightMargin = 0;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);
        actionBar.setBackgroundDrawable(new ColorDrawable(0x7D000000));

        String PayExOrderNumber = getIntent().getStringExtra("PayExOrderNumber");
        String PayExRefrenceNumber = getIntent().getStringExtra("PayExRefrenceNumber");
        String fNameDuo = getIntent().getStringExtra("FirstNameDuo");
        String lNameDuo = getIntent().getStringExtra("LastNameDuo");
        String emailDuo = getIntent().getStringExtra("EmailDuo");
        String paymentSubmited = getIntent().getStringExtra("Payment");
        String PaymentMethod = getIntent().getStringExtra("PaymentMethod");

        String [] params= SaveSharedPreference.getLoggedParams(getApplicationContext());
        Realm_User user= Repository.provideUserLocal(params[0],params[1]);



        fname = user.getF_name();
        lname = user.getL_name();
        Email =user.getEmail();
        password =user.getPass();
        dob = user.getDob();
        height =String.valueOf(user.getHeight_in_cm());
        weight =String.valueOf(user.getWeight());
        gender = user.getUser_gender();
        PaymentStatus = "true";
        userID = user.getId();

        OrderNumber=findViewById(R.id.orderNumber);
        RefrenceNumber=findViewById(R.id.refrenceNumber);
        EmailTxt=findViewById(R.id.emailText);


        OrderNumber.setText("Order Number : ".concat(PayExOrderNumber));

        RefrenceNumber.setText("Refrence Number : ".concat(PayExRefrenceNumber));

        EmailTxt.setText("We have send the payment recipt at your email ".concat(Email));

        paymentSucessPresenter = new PaymentSucessPresenter(PayExThankYou.this);

        paymentSucessPresenter.performPaymentSign_up(fname,lname,password,Email,gender,dob,height,weight,fNameDuo,lNameDuo,"","","",paymentSubmited,PaymentStatus,PaymentMethod,PayExRefrenceNumber,PayExOrderNumber,emailDuo,userID);


    }


    public void onNextClick(View view) {


        Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);

        startActivity(intent);

        finish();

        finishAffinity();



    }


    @Override
    public void setPaymentSucess(String message) {

    }

    @Override
    public void setPaymentFailed(String message) {

    }

    @Override
    public void setPresenter(PaymentSucessContract.Presenter mPresenter) {

    }
}
