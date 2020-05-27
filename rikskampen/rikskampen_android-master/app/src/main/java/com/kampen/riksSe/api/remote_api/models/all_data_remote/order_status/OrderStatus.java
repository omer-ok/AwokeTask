package com.kampen.riksSe.api.remote_api.models.all_data_remote.order_status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderStatus {



    @SerializedName("orderStatus")
    @Expose
    ArrayList<Order>   orderList;


}
