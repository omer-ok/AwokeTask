package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.user.model.Category;
import com.kampen.riksSe.user.model.TestItem;

import io.realm.Realm;
import io.realm.RealmResults;

public class Database_Manager {



    public static  void testItem(Realm  dataBase,String id,String name,String cat,String type,String manu)
    {


        dataBase.beginTransaction();

        TestItem person = dataBase.createObject(TestItem.class); // Create managed objects directly

        person.setName(name);

        person.setId(id);

        Category  category=new Category();
        category.setType(type);
        category.setManufacturer(manu);


        for(int i=0; i<5; i++) {

            final Category managedDog = dataBase.copyToRealm(category);
                  managedDog.setType(i+"");
                  //person.setCategory((new ArrayList<Category>().add(managedDog)));
        }

        dataBase.commitTransaction();


        RealmResults<Category> userList=dataBase.where(Category.class)

                .findAll();


       System.out.println(userList.size());


        dataBase.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                TestItem db_user = realm.createObject(TestItem.class);


                db_user.setId(id);
                db_user.setName(name);
                //db_user.setCategory(category);

            }
        });




    }








}
