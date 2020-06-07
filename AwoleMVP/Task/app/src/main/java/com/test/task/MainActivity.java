package com.test.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.test.task.Adapters.FlashProductAdapter;
import com.test.task.Model.Flash;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    Context mContext;
    LinearLayoutManager mLayoutManager,mLayoutManager1;
    public RecyclerView mFlashProductsRV,mHomeProductsRV;
    FlashProductAdapter mFlashProductAdapter,mHomeProductAdapter;

    int mCurrentPageNo=0;
    Boolean isLastPage,isLoading;
    MainActivityPresenter mainActivityPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;

        mFlashProductsRV = findViewById(R.id.FlashProductsRV);
        mHomeProductsRV = findViewById(R.id.HomeProductsRV);
        // Pagination
        mHomeProductsRV.addOnScrollListener(recyclerViewOnScrollListener);
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager1 = new LinearLayoutManager(mContext);

        mainActivityPresenter = new MainActivityPresenter(MainActivity.this);
        mainActivityPresenter.getAllFlashDataFromServer(mContext);
        mainActivityPresenter.getAllHomeDataFromServer(mContext,"1");



    }
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager1.getChildCount();
            int totalItemCount = mLayoutManager1.getItemCount();
            int firstVisibleItemPosition = mLayoutManager1.findFirstVisibleItemPosition();

            /*if (!isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 491) {
                    mCurrentPageNo +=1;
                    mainActivityPresenter.getAllHomeDataFromServer(mContext, String.valueOf(mCurrentPageNo));
                }
            }*/
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount <= 491) {
                mCurrentPageNo +=1;
                mainActivityPresenter.getAllHomeDataFromServer(mContext, String.valueOf(mCurrentPageNo));
            }
        }
    };
    @Override
    public void setAllFlashDataSucess(String message, Flash flash) {
        if(flash!=null){
            if(mFlashProductAdapter !=null){
                mFlashProductAdapter.updateAdapter(flash.getOUTPUT().getDATA().getITEMS());
            }else{
                mFlashProductAdapter = new FlashProductAdapter(mContext, flash.getOUTPUT().getDATA().getITEMS());
                mFlashProductsRV.setLayoutManager(getHorizontalLayoutManager(mContext));
                mFlashProductsRV.setAdapter(mFlashProductAdapter);
            }
        }
    }

    @Override
    public void setAllFlashDataFailed(String message) {

    }

    @Override
    public void setAllHomeDataSucess(String message, Flash flash) {
        isLoading=false;
        if(flash!=null){
            mCurrentPageNo = flash.getOUTPUT().getNAVIGATION().getPAGE();
            if(mCurrentPageNo==491){
                isLastPage=true;
            }
            if(mHomeProductAdapter !=null){
                mHomeProductAdapter.updateAdapter(flash.getOUTPUT().getDATA().getITEMS());
            }else{
                mHomeProductAdapter = new FlashProductAdapter(mContext, flash.getOUTPUT().getDATA().getITEMS());
                mHomeProductsRV.setLayoutManager(mLayoutManager1);
                mHomeProductsRV.setAdapter(mHomeProductAdapter);
            }
        }
    }

    @Override
    public void setAllHomeDataFailed(String message) {

    }

    @Override
    public void setPresenter(MainActivityContract.Presenter mPresenter) {

    }


    public static RecyclerView.LayoutManager  getHorizontalLayoutManager(Context context)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        return layoutManager;
    }
}
