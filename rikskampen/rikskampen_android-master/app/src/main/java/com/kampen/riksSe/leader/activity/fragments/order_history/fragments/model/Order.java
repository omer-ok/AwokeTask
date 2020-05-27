package com.kampen.riksSe.leader.activity.fragments.order_history.fragments.model;

import io.realm.RealmObject;

public class Order extends RealmObject {


    private  String    id;
    private  String    title;
    private  String    status;
    private  String    price;
    private  String    shortDes;

    private   String   path;

    private    int    tempImage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTempImage() {
        return tempImage;
    }

    public void setTempImage(int tempImage) {
        this.tempImage = tempImage;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }
}
