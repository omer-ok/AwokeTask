package com.kampen.riksSe.leader.activity.fragments.plans;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.UpdateLoginProfile.updateProfileStartOneActivity;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.squareup.picasso.Picasso;

import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;

public class IntroActivity extends AppCompatActivity {

    Button next;
    ImageView welcomeGIF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_video);
        //next = findViewById(R.id.button1);
        welcomeGIF = findViewById(R.id.welcomeGIF);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
        //  getSupportActionBar().setHomeAsUpIndicator(upArrow);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_actionbar);
        android.support.v7.app.ActionBar.LayoutParams layoutParams = new android.support.v7.app.ActionBar.LayoutParams(
                android.support.v7.app.ActionBar.LayoutParams.WRAP_CONTENT,
                android.support.v7.app.ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL
                *//*| Gravity.CENTER_VERTICAL*//*);
        layoutParams.rightMargin = 0;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);
        actionBar.setBackgroundDrawable(new ColorDrawable(0x7D000000));*/
        //next.setEnabled(true);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
               /*Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(NAVIGATING_FROM_TAG,"abc");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();*/

                String[] params = SaveSharedPreference.getLoggedParams(IntroActivity.this);

                Realm_User user = Repository.provideUserLocal(params[0], params[1]);
                double height = user.getHeight_in_cm();

                double weight = user.getWeight();

                double GoalWeight = user.getGoalweight();

                double waist = user.getWaist();
                String DOB = user.getDob();
                if(height<=0 || GoalWeight <=0 || weight <=0 || waist<=0||DOB==null){
                    Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                    intent.putExtra("ActivityStatus","BMI");
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString(NAVIGATING_FROM_TAG,"abc");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);

        GlideApp
                .with(IntroActivity.this)
                .load(R.drawable.welcom)
                .into(welcomeGIF);
       /* GlideApp
                .with(IntroActivity.this)
                .load(R.drawable.welcom)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        String[] params = SaveSharedPreference.getLoggedParams(IntroActivity.this);

                        Realm_User user = Repository.provideUserLocal(params[0], params[1]);
                        double height = user.getHeight_in_cm();
                        double weight = user.getWeight();
                        if(height<=0 || weight <=0){
                            Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                            intent.putExtra("ActivityStatus","BMI");
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString(NAVIGATING_FROM_TAG,"abc");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }

                        return false;
                    }

                })
                .into(welcomeGIF);*/
    }

    private void loadImage(final ImageView imageView, final String imageUrl) {

        /*Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();*/

        GlideApp
                .with(IntroActivity.this)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        String[] params = SaveSharedPreference.getLoggedParams(IntroActivity.this);

                        Realm_User user = Repository.provideUserLocal(params[0], params[1]);
                        double height = user.getHeight_in_cm();
                        double weight = user.getWeight();
                        if(height<=0 || weight <=0){
                            Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                            intent.putExtra("ActivityStatus","BMI");
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString(NAVIGATING_FROM_TAG,"abc");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }

                        return false;
                    }
                })
                .into(imageView);
    }

    /*public void  onNextClick(View view)
    {

        Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,"abc");
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
*/

   /* @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

}
