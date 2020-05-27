package com.kampen.riksSe.user.model;

import io.realm.RealmObject;

public class Category  extends RealmObject {


    private String  type;

    private String   manufacturer;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
