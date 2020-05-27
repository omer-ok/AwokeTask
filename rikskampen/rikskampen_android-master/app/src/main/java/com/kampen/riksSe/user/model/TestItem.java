package com.kampen.riksSe.user.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class TestItem extends RealmObject {


    private String   id;

    private String   name;

    private RealmList<Category> category;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public RealmList<Category> getCategory() {
        return category;
    }

    public void setCategory(RealmList<Category> category) {
        this.category = category;
    }
}
