package com.test.task.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.task.Model.ITEM;
import com.test.task.R;

import java.util.ArrayList;
import java.util.List;


public class FlashProductAdapter extends RecyclerView.Adapter<FlashProductAdapter.ViewHolder>  {


    List<ITEM> mProdecutsArrayList=new ArrayList<>();

    private  Context mContext;


    public FlashProductAdapter(Context context, List<ITEM> ProdecutsArrayList)
    {

        this.mProdecutsArrayList.addAll(ProdecutsArrayList);

        mContext=context;


    }
    public void  updateAdapter(List<ITEM> DaySchduleArrayList)
    {
        this.mProdecutsArrayList.clear();
        this.mProdecutsArrayList.addAll(DaySchduleArrayList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_flash_schdule, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(mProdecutsArrayList.get(i).getIMAGE()!=null){
            loadImage(viewHolder.mProductImage,mProdecutsArrayList.get(i).getIMAGE().getSRC());
        }
        viewHolder.mProductName.setText(mProdecutsArrayList.get(i).getNAME());
        if(mProdecutsArrayList.get(i).getPRICES()!=null){
            viewHolder.mProductOldPrice.setText("Old Price : "+mProdecutsArrayList.get(i).getPRICES().getPRICEOLD());
            viewHolder.mProductNewPrice.setText("New Price : "+mProdecutsArrayList.get(i).getPRICES().getPRICENEW());
            viewHolder.mDiscount.setText("Discount : "+mProdecutsArrayList.get(i).getPRICES().getDISCOUNT());
        }
        manageClick(i,viewHolder.mAddToCart);
    }



    @Override
    public int getItemCount() {
        return mProdecutsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        TextView mProductName,mProductOldPrice,mProductNewPrice,mDiscount;
        ImageView mProductImage,mAddToCart;
        public View view;


        public ViewHolder(View v) {
            super(v);
            mProductImage=v.findViewById(R.id.profileImage);
            mProductName=v.findViewById(R.id.productName);
            mProductOldPrice=v.findViewById(R.id.oldPrice);
            mProductNewPrice=v.findViewById(R.id.newPrice);
            mDiscount=v.findViewById(R.id.discount);
            mAddToCart=v.findViewById(R.id.addToCart);
            view=v;
        }
    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Toast.makeText(mContext, mProdecutsArrayList.get(position).getNAME(), Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.i("Error",ex.toString());
                }


            }
        });
    }
    private void loadImage(final ImageView imageView, final String imageUrl){

        Glide
                .with(mContext)
                .load(imageUrl)
                .error(R.drawable.avatar_new)
                .into(imageView);


    }

}
