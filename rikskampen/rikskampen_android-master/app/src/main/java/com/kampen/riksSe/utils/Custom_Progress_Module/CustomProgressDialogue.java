package com.kampen.riksSe.utils.Custom_Progress_Module;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.kampen.riksSe.R;
import com.wang.avi.AVLoadingIndicatorView;

public class CustomProgressDialogue   extends ProgressDialog {

    AVLoadingIndicatorView mAVLoadingIndicatorView;
    SpinKitView progressBar;
    Sprite threeBounce;
    FadingCircle fadingCircle;
    TextView progressMessage;

    String message="";

    public CustomProgressDialogue(Context context, String message) {
        super(context,ProgressDialog.THEME_HOLO_DARK);


        this.message=message;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.custom_progress_layout);



        progressBar = (SpinKitView) findViewById(R.id.spin_kit);
        threeBounce= new ThreeBounce();
        fadingCircle =new FadingCircle();
        progressBar.setIndeterminateDrawable(threeBounce);
        progressMessage= (TextView) findViewById(R.id.progressMessage);
     /*   mAVLoadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.triangleProgressView);
        progressMessage= (TextView) findViewById(R.id.progressMessage);
        mAVLoadingIndicatorView.show();*/
        progressMessage.setText(message);

    }


    public void StopView()
    {
        try {

            this.hide();
            if (mAVLoadingIndicatorView != null) {
                mAVLoadingIndicatorView.hide();
            }
        }catch (Exception ex){

        }
    }

}
