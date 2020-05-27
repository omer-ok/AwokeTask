package com.kampen.riksSe.payment.PayEx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.plans.SelectPlansActivity;

public class PayExPaymentWebViewActivity extends AppCompatActivity {

    TextView PaymentDisplay;
    String Payment;
    String payValue;
    WebView myWebView;
    Button closeWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payex_webview_activity);
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

        String PayExLink = getIntent().getStringExtra("PayExLink");
        String PayExOrderNumber = getIntent().getStringExtra("PayExOrderNumber");
        String PayExRefrenceNumber = getIntent().getStringExtra("PayExRefrenceNumber");
        String fNameDuo = getIntent().getStringExtra("FirstNameDuo");
        String lNameDuo = getIntent().getStringExtra("LastNameDuo");
        String emailDuo = getIntent().getStringExtra("EmailDuo");
        String paymentSubmited = getIntent().getStringExtra("Payment");
        String PaymentMethod = getIntent().getStringExtra("PaymentMethod");




        myWebView = (WebView) findViewById(R.id.PayexWebView);


        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                           Uri url = null;
                                           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                               url = request.getUrl();
                                           }
                                           if( url.toString().contains("http://example.com/payment-completed")) {
                                               // do something here



                                               Intent intent = new Intent(getApplicationContext(), PayExThankYou.class);
                                               intent.putExtra("PayExOrderNumber",PayExOrderNumber);
                                               intent.putExtra("PayExRefrenceNumber",PayExRefrenceNumber);
                                               intent.putExtra("FirstNameDuo",fNameDuo);
                                               intent.putExtra("LastNameDuo",lNameDuo);
                                               intent.putExtra("LastNameDuo",emailDuo);
                                               intent.putExtra("Payment",paymentSubmited);
                                               intent.putExtra("PaymentMethod",PaymentMethod);
                                               startActivity(intent);

                                               finish();

                                           }
                                           else if(url.toString().contains("https://example.com/payment-canceled")){

                                               Intent intent = new Intent(getApplicationContext(), SelectPlansActivity.class);
                                               startActivity(intent);
                                               finish();

                                           }

                                           return true;
                                       }
                                   });
        myWebView.loadUrl(PayExLink);



    }


}
