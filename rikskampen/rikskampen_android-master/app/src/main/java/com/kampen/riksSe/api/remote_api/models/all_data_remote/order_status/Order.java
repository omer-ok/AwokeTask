package com.kampen.riksSe.api.remote_api.models.all_data_remote.order_status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {


    @SerializedName("orderStatus")
    @Expose
    String  id;
    @SerializedName("orderDate")
    @Expose
    String  orderDate;
    @SerializedName("price")
    @Expose
    double  price;
    @SerializedName("price_unit")
    @Expose
    String  price_unit;
    @SerializedName("imagePath")
    @Expose
    String  imagePath;
    @SerializedName("qty")
    @Expose
    int  qty;
    @SerializedName("status")
    @Expose
    String  status;
    @SerializedName("title")
    @Expose
    String  title;
    @SerializedName("description")
    @Expose
    String   description;


}
