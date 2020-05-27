package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import org.w3c.dom.Text;

import java.io.File;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecipeSummary extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener, OnErrorListener {


    PDFView mPdfView;
    View noData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_summary);
        WebView myWebView = (WebView) findViewById(R.id.aboutweb);
        noData = (RelativeLayout) findViewById(R.id.NoDataView);
        TextView mErrorMsg = findViewById(R.id.errorMsgText);
        mPdfView = findViewById(R.id.pdfView);

        PRDownloader.initialize(getApplicationContext());

        String recipeSummaryUrl = SaveSharedPreference.getNutritionPDF(RecipeSummary.this);
        String FullPDFURL = "https://docs.google.com/gview?embedded=true&url="+recipeSummaryUrl;

        Uri pdfUIR= Uri.parse(FullPDFURL);

    /*    mPdfView.fromUri(pdfUIR)
                .load();*/
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        File file = new File(this.getExternalFilesDir(null), "Recipe");
        File downloadedFile = new File(file.getAbsolutePath(), "RecipeBook");
        mPdfView.fromFile(downloadedFile)
                .spacing(10)
                .onError(RecipeSummary.this)
                .load();


        /*if(isNetworkConnected()){
            if(recipeSummaryUrl.isEmpty() ){
                noData.setVisibility(View.VISIBLE);
                myWebView.setVisibility(View.GONE);
                mErrorMsg.setText("Inget recept PDF tillgängligt.");
            }else{
                noData.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
                myWebView.invalidate();
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                myWebView.getSettings().setAllowFileAccess(true);
                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        Uri url = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                            //url = request.getUrl();
                            //view.loadUrl(FullPDFURL);
                        }
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FullPDFURL));
                        startActivity(browserIntent);

                        return true;
                    }
                });
                myWebView.loadUrl(FullPDFURL);
                myWebView.reload();
            }
        }else{
            mErrorMsg.setText("No Internet Connection");
            noData.setVisibility(View.VISIBLE);
            myWebView.setVisibility(View.GONE);
            MyApplication.showSimpleSnackBar(RecipeSummary.this,"No Internet Connection");
        }*/
    }



    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    public String getRecipeFilePath(Context context){
        Realm_User  user=provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());

        String RecipeSummary = "";

        if(nutritiousDB!=null) {

            if (nutritiousDB.getRecipeSchedule() != null) {

                RecipeSummary = nutritiousDB.getRecipeSchedule();
            }
        }
        return RecipeSummary;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("www.google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    public void onBackClick(View view) {

        finish();

    }


    public void DownloadPDF(String URL,String FilePath,String FileName){
        int downloadId = PRDownloader.download(URL,FilePath , FileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        ProgressManager.showProgress(RecipeSummary.this,getResources().getString(R.string.progress_dialog_message));
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        ProgressManager.hideProgress();
                        mPdfView.setVisibility(View.VISIBLE);
                        noData.setVisibility(View.GONE);
                        File downloadedFile = new File(FilePath, FileName);
                        mPdfView.fromFile(downloadedFile)
                                .spacing(10)
                                .onError(RecipeSummary.this)
                                .load();
                        Toast.makeText(RecipeSummary.this, "PDF Downloaded", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Error error) {
                        Log.i("DownloadFail", String.valueOf(error.getResponseCode()));
                        if(error.getResponseCode()!=404){
                            ProgressManager.hideProgress();
                            mPdfView.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            File downloadedFile = new File(FilePath, FileName);
                            if(downloadedFile!=null){
                                mPdfView.fromFile(downloadedFile)
                                        .spacing(10)
                                        .onError(RecipeSummary.this)
                                        .load();
                            }else{
                                Toast.makeText(RecipeSummary.this, "No Offline PDF", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RecipeSummary.this, getResources().getString(R.string.HealthProgramsModule_PDF), Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(RecipeSummary.this, "PDF Download Failed But loading from Storage", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void onPageError(int page, Throwable t) {
        //Toast.makeText(RecipeSummary.this, "No Offline PDF", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable t) {
        ProgressManager.hideProgress();
        Log.i("PDFError",t.getMessage());
        if(t.getMessage().equals("No such file or directory")){

            if(isNetworkConnected()){
                String recipeSummaryUrl = SaveSharedPreference.getNutritionPDF(RecipeSummary.this);
                File file = new File(this.getExternalFilesDir(null), "Recipe");
                DownloadPDF(recipeSummaryUrl,file.getAbsolutePath(),"RecipeBook");
            }else{
                Toast.makeText(RecipeSummary.this, getResources().getString(R.string.HealthProgramsModule_PDF), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, getResources().getString(R.string.General_NoInternetConnection), Toast.LENGTH_SHORT).show();
            }

        }
        mPdfView.setVisibility(View.GONE);
        noData.setVisibility(View.VISIBLE);
    }
}
