package com.kampen.riksSe.payment.PayEx;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.plans.SelectPlansActivity;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.Invoice;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayExInvoiceSendData;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayeeInfoInvoice;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PaymentInvoice;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PriceInvoice;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.UrlsInvoice;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayExSendData;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayeeInfo;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PaymentSend;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.Price;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.Urls;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PayExPaymentActivity extends AppCompatActivity implements PayExPaymentContract.View{

    TextView PaymentDisplay,discount,discountedPayment;
    String Payment,Status;
    String payValue,DiscountedPrice;
    EditText promoCode;
    PayExPaymentPresenter payExPaymentPresenter;

    String PayExLink,PayExRefrenceNumber,PayExInvoiceLink,PayExInvoiceRefrenceNumber,Email,fname,lname,password,gender,height,weight,dob;
    double PayExOrderNumber,PayExInvoiceOrderNumber;

    String fNameDuo;
    String lNameDuo;
    String passDuo;
    String emailDuo;
    String paymentSubmited;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payex_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

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


        promoCode = findViewById(R.id.getPromoCode);

        Status =getIntent().getStringExtra("Status");

        if(Status.equals("Single")){

            Payment =getIntent().getStringExtra("PaymentResponce");

        }
        else{

            fNameDuo = getIntent().getStringExtra("FirstNameDuo");
            lNameDuo = getIntent().getStringExtra("LastNameDuo");
            emailDuo = getIntent().getStringExtra("EmailDuo");
            Payment =getIntent().getStringExtra("PaymentResponce");
        }



        PaymentDisplay = (TextView) findViewById(R.id.PaymentDisplay);

        discount = (TextView) findViewById(R.id.discountedPaymentDisplay);

        discountedPayment = (TextView) findViewById(R.id.discountedacctualPaymentDisplay);

        PaymentDisplay.setText(Payment);




        payValue = Payment.replace(" kr","");

        int accPAy = Integer.parseInt(payValue);

        paymentSubmited = String.valueOf(accPAy);

        String [] params= SaveSharedPreference.getLoggedParams(getApplicationContext());

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("mmyyyy");
        String formattedDate = df.format(c);

        PayExRefrenceNumber = "ARK".concat(user.getId()).concat(formattedDate);
        PayExInvoiceRefrenceNumber="ARK".concat(user.getId()).concat(formattedDate);


        payExPaymentPresenter =new PayExPaymentPresenter(PayExPaymentActivity.this,PayExPaymentActivity.this);



        ProgressManager.showProgress(PayExPaymentActivity.this,"Please Wait...");

        payExPaymentPresenter.SetPayExPayments(PayExCardPaymentInt(accPAy));
        payExPaymentPresenter.SetPAyExInvoicePayment(PayExInvoiceInit(accPAy));

    }

    public PayExSendData PayExCardPaymentInt(int amount){


        //PayEx Direct Payment Object Start//

        PayeeInfo payeeInfo=new PayeeInfo();

        payeeInfo.setPayeeId("8022b0d7-4136-47e9-ba05-6f34a9478534");
        payeeInfo.setPayeeReference(PayExRefrenceNumber);
        payeeInfo.setPayeeName("Rikskampen Sverige AB");
        payeeInfo.setProductCategory("A123");
        payeeInfo.setOrderReference("or-12456");
        payeeInfo.setSubsite("MySubsite");

        Urls urls = new Urls();

        List<String> hostUrls =new ArrayList<String>();
        hostUrls.add("http://demo.rikskampen.se");

        urls.setHostUrls(hostUrls);
        urls.setCompleteUrl( "http://example.com/payment-completed");
        urls.setCancelUrl("https://example.com/payment-canceled");
        urls.setCallbackUrl("http://example.com/payment-callback");
        urls.setLogoUrl("https://riks.rikskampen.se/storage/settings/January2019/Aj1dnotWtflQJ3ovXYwp.png");
        urls.setTermsOfServiceUrl("https://example.com/terms.pdf");

        Price price =new Price();
        price.setType("CreditCard");
        price.setAmount(amount);
        price.setVatAmount(0);

        PaymentSend paymentSend =new PaymentSend();

        paymentSend.setOperation("Purchase");
        paymentSend.setIntent("Authorization");
        paymentSend.setCurrency("SEK");
        List<Price> priceList =new ArrayList<Price>();
        priceList.add(price);
        paymentSend.setPrices(priceList);


        paymentSend.setDescription("Your Refrence Number");
        paymentSend.setPayerReference(PayExRefrenceNumber);
        paymentSend.setGeneratePaymentToken(false);
        paymentSend.setUserAgent("Android");
        paymentSend.setLanguage("sv-SE");
        paymentSend.setUrls(urls);
        paymentSend.setPayeeInfo(payeeInfo);

        PayExSendData payExSendData =new PayExSendData();

        payExSendData.setPayment(paymentSend);

        //PayEx Direct Payment Object End //

        return payExSendData;
    }

    public PayExInvoiceSendData PayExInvoiceInit(int amount){


        //PayEx Invoice Payment Object Start//

        Invoice invoice =new Invoice();

        invoice.setInvoiceType("PayExFinancingSe");

        PayeeInfoInvoice payeeInfoInvoice =new PayeeInfoInvoice();

        payeeInfoInvoice.setPayeeId("8022b0d7-4136-47e9-ba05-6f34a9478534");
        payeeInfoInvoice.setPayeeReference(PayExInvoiceRefrenceNumber);
        payeeInfoInvoice.setPayeeName("Merchant1");
        payeeInfoInvoice.setProductCategory("PC1234");
        payeeInfoInvoice.setOrderReference("or-12456");
        payeeInfoInvoice.setSubsite("MySubsite");

        UrlsInvoice urlsInvoice = new UrlsInvoice();

        urlsInvoice.setCompleteUrl("http://example.com/payment-completed");
        urlsInvoice.setCancelUrl("https://example.com/payment-canceled");
        urlsInvoice.setCallbackUrl( "https://example.com/payment-callback");
        List<String> hostinUrls =new ArrayList<>();
        hostinUrls.add("https://example.com");
        hostinUrls.add("https://example.net");
        urlsInvoice.setHostUrls(hostinUrls);
        urlsInvoice.setLogoUrl("https://riks.rikskampen.se/storage/settings/January2019/Aj1dnotWtflQJ3ovXYwp.png");
        urlsInvoice.setTermsOfServiceUrl("https://example.com/terms.pdf");

        PriceInvoice priceInvoice =new PriceInvoice();

        priceInvoice.setType("Invoice");
        priceInvoice.setAmount(amount);
        priceInvoice.setVatAmount(0);
        List<PriceInvoice> priceInvoiceList =new ArrayList<>();
        priceInvoiceList.add(priceInvoice);

        PaymentInvoice paymentInvoice =new PaymentInvoice();

        paymentInvoice.setOperation("FinancingConsumer");
        paymentInvoice.setIntent("Authorization");
        paymentInvoice.setCurrency("SEK");
        paymentInvoice.setPrices(priceInvoiceList);
        paymentInvoice.setDescription("Test Purchase");
        paymentInvoice.setPayerReference("RK2");
        paymentInvoice.setUserAgent("android");
        paymentInvoice.setLanguage("sv-SE");
        paymentInvoice.setUrls(urlsInvoice);
        paymentInvoice.setPayeeInfo(payeeInfoInvoice);


        PayExInvoiceSendData payExInvoiceSendData =new PayExInvoiceSendData();

        payExInvoiceSendData.setInvoice(invoice);
        payExInvoiceSendData.setPayment(paymentInvoice);

        //PayEx Invoice Payment Object End//


        return payExInvoiceSendData;
    }

    public void onPayExCardClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PayExPaymentWebViewActivity.class);
        intent.putExtra("PayExLink",PayExLink);
        intent.putExtra("PayExOrderNumber",String.valueOf(PayExOrderNumber));
        intent.putExtra("PayExRefrenceNumber",PayExRefrenceNumber);
        intent.putExtra("FirstNameDuo",fNameDuo);
        intent.putExtra("LastNameDuo",lNameDuo);
        intent.putExtra("EmailDuo",emailDuo);
        intent.putExtra("Payment",paymentSubmited);
        intent.putExtra("PaymentMethod","PayExCardPayment");

        startActivity(intent);

        finish();

    }


    public void onPayExInvoiceClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PayExPaymentWebViewActivity.class);
        intent.putExtra("PayExLink",PayExInvoiceLink);
        intent.putExtra("PayExOrderNumber",String.valueOf(PayExInvoiceOrderNumber));
        intent.putExtra("PayExRefrenceNumber",PayExRefrenceNumber);
        intent.putExtra("FirstNameDuo",fNameDuo);
        intent.putExtra("LastNameDuo",lNameDuo);
        intent.putExtra("EmailDuo",emailDuo);
        intent.putExtra("Payment",paymentSubmited);
        intent.putExtra("PaymentMethod","PayExInvoicePayment");
        startActivity(intent);

        finish();

    }



    public void onClickPromo(View view) {

        String PromoCode =promoCode.getText().toString();
        if(!PromoCode.isEmpty() ){

            String accPAy = payValue;

            ProgressManager.showProgress(PayExPaymentActivity.this,"Please Wait...");

            payExPaymentPresenter.SetPromoCode("2",accPAy,PromoCode);

            //String DiscountPAy = String.valueOf(getDiscountedAmount(accPAy,  100));



        }

    }

    private double getDiscountedAmount(int actualAmount,int discountCode){

        double  dis,amount,markedprice,s;

        dis=25;

        markedprice=1000;

        s=100-dis;

        amount= (s*actualAmount)/100;


        return amount;
    }


    @Override
    public void setPayExPaymentSucess(String message,double orderNumber) {

        PayExLink=message;
        PayExOrderNumber=orderNumber;
        Log.i("PayExLink",PayExLink);
        Log.i("PayExOrderNumber",String.valueOf(PayExOrderNumber));
        ProgressManager.hideProgress();
    }

    @Override
    public void setPayExPaymentFailed(String message) {

    }

    @Override
    public void setPayExInvoicePaymentSucess(String message, double orderNumber) {

        PayExInvoiceLink=message;
        PayExInvoiceOrderNumber=orderNumber;
        Log.i("PayExInvoiceLink",PayExInvoiceLink);
        Log.i("PayExInvoiceOrderNumber",String.valueOf(PayExInvoiceOrderNumber));
        ProgressManager.hideProgress();
    }

    @Override
    public void setPayExInvoicePaymentFailed(String message) {

    }

    @Override
    public void setPromoCodeSucess(String message,String DiscountedAmount,String discountedPrice) {

        DiscountedPrice= String.valueOf(discountedPrice);
        discount.setText(DiscountedAmount.concat(" Kr"));
        discountedPayment.setText(discountedPrice.concat(" Kr"));

        ProgressManager.hideProgress();

        int amount =0;

        try{

          double  disAmount = Double.valueOf(DiscountedPrice);
          amount = (int) disAmount;

            paymentSubmited = String.valueOf(amount);

        }catch(NumberFormatException ex){ // handle your exception

        }


        payExPaymentPresenter.SetPayExPayments(PayExCardPaymentInt(amount));
        payExPaymentPresenter.SetPAyExInvoicePayment(PayExInvoiceInit(amount));



    }

    @Override
    public void setPromoCodeFailed(String message) {


        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(PayExPaymentActivity.this, "No Internet Connection");
            ProgressManager.hideProgress();
        }
        else{
            MyApplication.showSimpleSnackBar(PayExPaymentActivity.this, message);
            ProgressManager.hideProgress();
        }
    }

    @Override
    public void setPresenter(PayExPaymentContract.Presenter mPresenter) {

    }



    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(getApplicationContext(), SelectPlansActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

}
