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

public class CompletedOrderAdapter extends RecyclerView.Adapter<CompletedOrderAdapter.ViewHolder>  {


    List<Order> list;


   public CompletedOrderAdapter()
    {
        list=getData();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_completed_order, viewGroup, false);

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
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public ImageView productImage;
        public TextView productName;
        public  TextView  shortSummary;
        public   TextView  productPrice;

        public  View view;



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


            Order  order1=new Order();

            order1.setId("");

            order1.setPrice("100$");

            order1.setTitle("Bottle Bench");

            order1.setTempImage(R.drawable.ic_product_1);

            order1.setShortDes("A safe thing");

            list.add(order1);



            Order  order2=new Order();

            order2.setId("");

            order2.setPrice("500$");

            order2.setTitle("Clock");

            order2.setTempImage(R.drawable.ic_clock);

            order2.setShortDes("A clock thing");

            list.add(order2);


            Order  order3=new Order();

            order3.setId("");

            order3.setPrice("127$");

            order3.setTitle("Band");

            order3.setShortDes("A band thing");

            order3.setTempImage(R.drawable.ic_band);

            list.add(order3);
        }

        return  list;
    }

}
