package com.kampen.riksSe.leader.activity.fragments.plans;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kampen.riksSe.R;
import com.kampen.riksSe.payment.PayEx.PayExPaymentActivity;
import com.kampen.riksSe.signup.SignUpDuoActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlanDetailActivity extends AppCompatActivity {



    private LinearLayout containerLayout;

    private TextView prizeTitle,planType,planRange,planSinglePrice,planDoublePrice;

    boolean isPlanSelected=false;

    RadioGroup   group;

    String Payment1,Payment2,ProductIDSingle,ProductIDDuo,ImageUrl,Title,FirstPrice,Age,InstallmentPlanSingle,InstallmentPlanDuo;

    private RadioButton radioButton;

    ImageView PackageImg;
    TextView firstPrice,age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

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




        containerLayout=findViewById(R.id.containerLayout);

        firstPrice =findViewById(R.id.price);
        age = findViewById(R.id.Age);
       // prizeTitle=findViewById(R.id.prizeTitle);
        planRange=findViewById(R.id.planRange);
        planType=findViewById(R.id.planType);
        planRange=findViewById(R.id.planRange);
        planSinglePrice=findViewById(R.id.planSinglePrice);
        planDoublePrice=findViewById(R.id.planDoublePrice);
        PackageImg=findViewById(R.id.packaegImg);

        group=findViewById(R.id.radioGroup);



         int   selectedPlan  =getIntent().getIntExtra("value",1);
         Payment1 =getIntent().getStringExtra("Payment1");
         Payment2 =getIntent().getStringExtra("Payment2");
         FirstPrice =getIntent().getStringExtra("FirstPrice");
         ProductIDSingle =getIntent().getStringExtra("ProductIDSingle");
         ProductIDDuo =getIntent().getStringExtra("ProductIDDuo");
         ImageUrl =getIntent().getStringExtra("ImageUrl");
         Title =getIntent().getStringExtra("Title");
         Age =getIntent().getStringExtra("Age");
         InstallmentPlanSingle =getIntent().getStringExtra("InstallmentPlanSingle");
         InstallmentPlanDuo =getIntent().getStringExtra("InstallmentPlanDuo");

        setLayout(selectedPlan);


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                isPlanSelected=true;

            }
        });



    }


    private void setLayout(int plan)
     {
        // int drawable=plan

         switch(plan)
         {
             case  1:
             {

                 containerLayout.setBackgroundResource(R.drawable.plan_item_1_bg);

                // prizeTitle.setText("1st prize 25000 kr.");
                 loadImage(PackageImg,ImageUrl);
                 age.setText(Age.replace("<p> </p>",""));
                 firstPrice.setText(FirstPrice);
                 planRange.setText(Age);
                 planType.setText(Title);
                 planSinglePrice.setText(Payment1.concat(" Kr ").concat(InstallmentPlanSingle).concat(" Single"));
                 planDoublePrice.setText(Payment2.concat(" Kr ").concat(InstallmentPlanDuo).concat(" Duo"));


                 break;
             }
             case  2:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_2_bg);

                // prizeTitle.setText("1st prize 50000 kr.");
                 loadImage(PackageImg,ImageUrl);
                 age.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planRange.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planType.setText(Title);
                 planSinglePrice.setText(Payment1.concat(" Kr").concat(InstallmentPlanSingle).concat(" Single"));
                 planDoublePrice.setText(Payment2.concat(" Kr").concat(InstallmentPlanDuo).concat(" Duo"));
                 break;
             }
             case  3:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_3_bg);

                 //prizeTitle.setText("1st prize 50000 kr.");
                 loadImage(PackageImg,ImageUrl);
                 age.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planRange.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planType.setText(Title);
                 planSinglePrice.setText(Payment1.concat(" Kr").concat(InstallmentPlanSingle).concat(" Single"));
                 planDoublePrice.setText(Payment2.concat(" Kr").concat(InstallmentPlanDuo).concat(" Duo"));
                 break;
             }
             case  4:
             {
                 containerLayout.setBackgroundResource(R.drawable.plan_item_4_bg);

                // prizeTitle.setText("1st prize 50000 kr.");
                 loadImage(PackageImg,ImageUrl);
                 age.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planRange.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planType.setText(Title);
                 planSinglePrice.setText(Payment1.concat(" Kr").concat(InstallmentPlanSingle).concat(" Single"));
                 planDoublePrice.setText(Payment2.concat(" Kr").concat(InstallmentPlanDuo).concat(" Duo"));

                 break;
             }
             case  5:
             {

                 //prizeTitle.setText("1st prize 100000 kr.");
                 loadImage(PackageImg,ImageUrl);
                 age.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planRange.setText(Age);
                 firstPrice.setText(FirstPrice);
                 planType.setText(Title);
                 planSinglePrice.setText(Payment1.concat(" Kr").concat(InstallmentPlanSingle).concat(" Single"));
                 planDoublePrice.setText(Payment2.concat(" Kr").concat(InstallmentPlanDuo).concat(" Duo"));

                 containerLayout.setBackgroundResource(R.drawable.plan_item_5_bg);
                 break;
             }
         }

     }


    public void onNextClick(View view) {

        //PaymentActivity
        //Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);

        // get selected radio button from radioGroup
        int selectedId = group.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

       // Toast.makeText(getApplicationContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();

        if(radioButton.getText().toString().equals("1")){



            Toast.makeText(getApplicationContext(),Payment1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), PayExPaymentActivity.class);
            intent.putExtra("Status","Single");
            intent.putExtra("PaymentResponce",Payment1);
            startActivity(intent);
            finish();
        }
        else{


            Toast.makeText(getApplicationContext(), Payment2, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), SignUpDuoActivity.class);
            intent.putExtra("PaymentResponce",Payment2);
            startActivity(intent);
            finish();
        }




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


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onTopClick(View view) {


        group.check(R.id.top);

    }

    public void onBottomClick(View view) {


        group.check(R.id.down);
    }



    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(getApplicationContext(), SelectPlansActivity.class);
        startActivity(intent);
        finish();
        return true;
    }


}
