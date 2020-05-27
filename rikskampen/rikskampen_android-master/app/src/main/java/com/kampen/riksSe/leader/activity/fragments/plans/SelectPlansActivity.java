package com.kampen.riksSe.leader.activity.fragments.plans;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanDetails;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectPlansActivity extends AppCompatActivity implements PlanContract.View{


    ImageView packageImg1,packageImg2,packageImg3,packageImg4;

    TextView firstPriceJun,ageJun,firstPriceVux,ageVux,firstPriceSenior,ageSenior,firstPricePremium,agePremium;

    TextView packageTitleJun,packageTitleVux,packageTitleSenior,packageTitlePremium;

    TextView PackageDesJun1,PackageDesJun2,PackageDesVux1,PackageDesVux2,PackageDesSenior1,PackageDesSenior2,PackageDesPremium1,PackageDesPreimum2;

    TextView PaymentJun1,PaymentJun2,PaymentVux1,PaymentVux2,PaymentSen1,PaymentSen2,PaymentVet1,PaymentVet2,PaymentElt1,PaymentElt2;


    PlanPresenter planPresenter;

    List<PlanDetails> planDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plans);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
        // getSupportActionBar().setHomeAsUpIndicator(upArrow);

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


        packageImg1 = (ImageView) findViewById(R.id.packaegImgJun);
        packageImg2 = (ImageView) findViewById(R.id.packaegImgVux);
        packageImg3 = (ImageView) findViewById(R.id.packaegImgSenior);
        packageImg4 = (ImageView) findViewById(R.id.packaegImgVeteran);


        firstPriceJun =(TextView) findViewById(R.id.priceJun);
        firstPriceVux =(TextView) findViewById(R.id.priceVux);
        firstPriceSenior =(TextView) findViewById(R.id.priceSenior);
        firstPricePremium =(TextView) findViewById(R.id.priceVeteran);

        ageJun = (TextView) findViewById(R.id.AgeJun);
        ageVux = (TextView) findViewById(R.id.AgeVux);
        ageSenior = (TextView) findViewById(R.id.AgeSenior);
        agePremium = (TextView) findViewById(R.id.AgeVeteran);

        packageTitleJun = (TextView) findViewById(R.id.TitleJun);
        packageTitleVux = (TextView) findViewById(R.id.TitleVux);
        packageTitleSenior = (TextView) findViewById(R.id.TitleSenior);
        packageTitlePremium = (TextView) findViewById(R.id.TitlePremium);

        PackageDesJun1 =(TextView) findViewById(R.id.paymentDesJun1);
        PackageDesJun2 =(TextView) findViewById(R.id.paymentDesJun2);
        PackageDesVux1 =(TextView) findViewById(R.id.paymentDesVux1);
        PackageDesVux2 =(TextView) findViewById(R.id.paymentDesVux2);
        PackageDesSenior1 =(TextView) findViewById(R.id.paymentDesSenior1);
        PackageDesSenior2 =(TextView) findViewById(R.id.paymentDesSenior2);
        PackageDesPremium1 =(TextView) findViewById(R.id.paymentDesPremium1);
        PackageDesPreimum2 =(TextView) findViewById(R.id.paymentDesPremium2);


        PaymentJun1 =(TextView) findViewById(R.id.paymentValueJun1);
        PaymentJun2 =(TextView) findViewById(R.id.paymentValueJun2);
        PaymentVux1 =(TextView) findViewById(R.id.paymentValueVux1);
        PaymentVux2 =(TextView) findViewById(R.id.paymentValueVux2);
        PaymentSen1 =(TextView) findViewById(R.id.paymentValueSen1);
        PaymentSen2 =(TextView) findViewById(R.id.paymentValueSun2);
        PaymentVet1 =(TextView) findViewById(R.id.paymentValueVet1);
        PaymentVet2 =(TextView) findViewById(R.id.paymentValueVet2);
/*        PaymentElt1 =(TextView) findViewById(R.id.paymentValueElt1);
        PaymentElt2 =(TextView) findViewById(R.id.paymentValueElt2);*/

        planPresenter =new PlanPresenter(SelectPlansActivity.this,SelectPlansActivity.this);
        planPresenter.getAllPlanData();

/*

        planDetailsList = (List<PlanDetails>) planPresenter.getPlansAllDataLocal(SelectPlansActivity.this);

        if(planDetailsList != null) {

            setPackages(planDetailsList);

        }*/



    }

    public void setPackages(List<PlanDetails> planDetailsList){
        loadImage(packageImg1, planDetailsList.get(0).getImageDuo());
        loadImage(packageImg2, planDetailsList.get(1).getImageDuo());
        loadImage(packageImg3, planDetailsList.get(2).getImageDuo());
        loadImage(packageImg4, planDetailsList.get(3).getImageDuo());


        firstPriceJun.setText(planDetailsList.get(0).getPrize());
        firstPriceVux.setText(planDetailsList.get(1).getPrize());
        firstPriceSenior.setText(planDetailsList.get(2).getPrize());
        firstPricePremium.setText(planDetailsList.get(3).getPrize());

        ageJun.setText(planDetailsList.get(0).getFeaturesSingle().replace("<p> </p>",""));
        ageVux.setText(planDetailsList.get(1).getFeaturesSingle().replace("<p> </p>",""));
        ageSenior.setText(planDetailsList.get(2).getFeaturesSingle().replace("<p> </p>",""));
        agePremium.setText(planDetailsList.get(3).getFeaturesSingle().replace("<p> </p>",""));

        PaymentJun1.setText(planDetailsList.get(0).getCostSingle());
        PaymentJun2.setText(planDetailsList.get(0).getCostDuo());
        PaymentVux1.setText(planDetailsList.get(1).getCostSingle());
        PaymentVux2.setText(planDetailsList.get(1).getCostDuo());
        PaymentSen1.setText(planDetailsList.get(2).getCostSingle());
        PaymentSen2.setText(planDetailsList.get(2).getCostDuo());
        PaymentVet1.setText(planDetailsList.get(3).getCostSingle());
        PaymentVet2.setText(planDetailsList.get(3).getCostDuo());


        PackageDesJun1.setText(planDetailsList.get(0).getInstallmentsPlanSingle());
        PackageDesJun2.setText(planDetailsList.get(0).getInstallmentsPlanDuo());
        PackageDesVux1.setText(planDetailsList.get(1).getInstallmentsPlanSingle());
        PackageDesVux2.setText(planDetailsList.get(1).getInstallmentsPlanDuo());
        PackageDesSenior1.setText(planDetailsList.get(2).getInstallmentsPlanSingle());
        PackageDesSenior2.setText(planDetailsList.get(2).getInstallmentsPlanDuo());
        PackageDesPremium1.setText(planDetailsList.get(3).getInstallmentsPlanSingle());
        PackageDesPreimum2.setText(planDetailsList.get(3).getInstallmentsPlanDuo());


    }


    public void onPlanOneClick(View view) {


        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",1);
        intent.putExtra("Payment1",PaymentJun1.getText().toString());
        intent.putExtra("Payment2",PaymentJun2.getText().toString());
        intent.putExtra("FirstPrice",planDetailsList.get(0).getPrize());
        intent.putExtra("ProductIDSingle",planDetailsList.get(0).getIdSingle());
        intent.putExtra("ProductIDDuo",planDetailsList.get(0).getIdDuo());
        intent.putExtra("ImageUrl",planDetailsList.get(0).getImageDuo());
        intent.putExtra("Title",planDetailsList.get(0).getTitleSingle().replace("SOLO",""));
        intent.putExtra("Age",planDetailsList.get(0).getFeaturesDuo());
        intent.putExtra("InstallmentPlanSingle",planDetailsList.get(0).getInstallmentsPlanSingle());
        intent.putExtra("InstallmentPlanDuo",planDetailsList.get(0).getInstallmentsPlanDuo());


        startActivity(intent);
        finish();

    }

    public void onPlanTwoClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",2);
        intent.putExtra("Payment1",PaymentVux1.getText().toString());
        intent.putExtra("Payment2",PaymentVux2.getText().toString());
        intent.putExtra("FirstPrice",planDetailsList.get(1).getPrize());
        intent.putExtra("ProductIDSingle",planDetailsList.get(1).getIdSingle());
        intent.putExtra("ProductIDDuo",planDetailsList.get(1).getIdDuo());
        intent.putExtra("ImageUrl",planDetailsList.get(1).getImageDuo());
        intent.putExtra("Title",planDetailsList.get(1).getTitleSingle().replace("SOLO",""));
        intent.putExtra("Age",planDetailsList.get(1).getFeaturesDuo());
        intent.putExtra("InstallmentPlanSingle",planDetailsList.get(1).getInstallmentsPlanSingle());
        intent.putExtra("InstallmentPlanDuo",planDetailsList.get(1).getInstallmentsPlanDuo());
        startActivity(intent);
        finish();

    }



    public void onPlanThreeClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",3);
        intent.putExtra("Payment1",PaymentSen1.getText().toString());
        intent.putExtra("Payment2",PaymentSen2.getText().toString());
        intent.putExtra("FirstPrice",planDetailsList.get(2).getPrize());
        intent.putExtra("ProductIDSingle",planDetailsList.get(2).getIdSingle());
        intent.putExtra("ProductIDDuo",planDetailsList.get(2).getIdDuo());
        intent.putExtra("ImageUrl",planDetailsList.get(2).getImageDuo());
        intent.putExtra("Title",planDetailsList.get(2).getTitleSingle().replace("SOLO",""));
        intent.putExtra("Age",planDetailsList.get(2).getFeaturesDuo());
        intent.putExtra("InstallmentPlanSingle",planDetailsList.get(2).getInstallmentsPlanSingle());
        intent.putExtra("InstallmentPlanDuo",planDetailsList.get(2).getInstallmentsPlanDuo());
        startActivity(intent);
        finish();

    }

    public void onPlanFourClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",4);
        intent.putExtra("Payment1",PaymentVet1.getText().toString());
        intent.putExtra("Payment2",PaymentVet2.getText().toString());
        intent.putExtra("FirstPrice",planDetailsList.get(3).getPrize());
        intent.putExtra("ProductIDSingle",planDetailsList.get(3).getIdSingle());
        intent.putExtra("ProductIDDuo",planDetailsList.get(3).getIdDuo());
        intent.putExtra("ImageUrl",planDetailsList.get(3).getImageDuo());
        intent.putExtra("Title",planDetailsList.get(3).getTitleSingle().replace("SOLO",""));
        intent.putExtra("Age",planDetailsList.get(3).getFeaturesDuo());
        intent.putExtra("InstallmentPlanSingle",planDetailsList.get(3).getInstallmentsPlanSingle());
        intent.putExtra("InstallmentPlanDuo",planDetailsList.get(3).getInstallmentsPlanDuo());
        startActivity(intent);
        finish();

    }

    public void onPlanFiveClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",5);
        intent.putExtra("Payment1",PaymentElt1.getText().toString());
        intent.putExtra("Payment2",PaymentElt2.getText().toString());
        startActivity(intent);
        finish();

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setPlanSucessfull(String message) {

        Log.i("Package Data S",message);
        planDetailsList = (List<PlanDetails>) planPresenter.getPlansAllDataLocal(SelectPlansActivity.this);
        if(planDetailsList != null) {

            setPackages(planDetailsList);

        }

    }


    @Override
    public void setPlanFailded(String message) {

        Log.i("Package Data F",message);
    }

    @Override
    public void setPresenter(PlanContract.Presenter mPresenter) {

    }


    private void loadImage(final ImageView imageView, final String imageUrl){
        Picasso.get()
                .load("http://riks.rikskampen.se/".concat(imageUrl))
                .into(imageView , new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });
    }
}
