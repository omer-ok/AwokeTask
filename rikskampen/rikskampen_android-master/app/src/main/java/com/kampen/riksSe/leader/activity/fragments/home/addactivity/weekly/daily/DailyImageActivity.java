package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.kampen.riksSe.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DailyImageActivity extends AppCompatActivity {


    ImageView dailyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_image_activity);

        dailyImage = findViewById(R.id.dailyimage);

        String Image = getIntent().getStringExtra("Image");


        loadImage(dailyImage,Image);

    }

    private void loadImage(final ImageView imageView, final String imageUrl){
        Picasso.get()
                .load(imageUrl)
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
