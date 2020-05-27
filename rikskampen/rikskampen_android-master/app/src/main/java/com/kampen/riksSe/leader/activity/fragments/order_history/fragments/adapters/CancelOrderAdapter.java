package com.kampen.riksSe.leader.activity.fragments.order_history.fragments.adapters;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.model.Order;
import com.kampen.riksSe.leader.activity.fragments.order_history.webshop.WebShopActivity;

import java.util.ArrayList;
import java.util.List;

public class CancelOrderAdapter extends RecyclerView.Adapter<CancelOrderAdapter.ViewHolder>  {


    List<Order>  list;


    public  CancelOrderAdapter()
    {
        list=getData();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_canceled_order, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {



        viewHolder.productImage.setImageResource(list.get(i).getTempImage());
        viewHolder.productName.setText(list.get(i).getTitle());
        viewHolder.shortSummary.setText(list.get(i).getShortDes());
        viewHolder.productPrice.setText(list.get(i).getPrice());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(viewHolder.view.getContext(), WebShopActivity.class);
                viewHolder.view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public ImageView productImage;
        public TextView   productName;
        public  TextView  shortSummary;
        public   TextView  productPrice;

          public   View   view;

        public ViewHolder(View v) {
            super(v);

            productImage=v.findViewById(R.id.productImage);
            productName=v.findViewById(R.id.productName);
            shortSummary=v.findViewById(R.id.shortSummary);
            productPrice=v.findViewById(R.id.productPrice);
            view=v;

        }


    }




    public   List<Order>   getData()
    {
        List<Order>  list=new ArrayList<>();

       // for(int i=0; i<4; i++)
        {
            Order  order=new Order();

            order.setId("");

            order.setPrice("120$");

            order.setTitle("Decline Bench");

            order.setTempImage(R.drawable.ic_declinebench);

            order.setStatus("20-12-2018");

            order.setShortDes("Decline Barbell Bench Press");


            list.add(order);


        }

        return  list;
    }
}
