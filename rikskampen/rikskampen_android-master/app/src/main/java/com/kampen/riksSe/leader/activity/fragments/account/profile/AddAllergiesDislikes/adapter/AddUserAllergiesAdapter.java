package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.UserAlergies;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddUserAllergiesAdapter extends RecyclerView.Adapter<AddUserAllergiesAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    public List<UserAlergies> imageModelArrayList;
    public List<UserAlergies> newUserAlergyArrayList;
    private Context ctx;
    private Realm mLocalService;

   public ArrayList<String> alergyUserIDRemovedList;

    public AddUserAllergiesAdapter(Context ctx) {

        inflater = LayoutInflater.from(ctx);
        this.newUserAlergyArrayList = new ArrayList<>();
        this.alergyUserIDRemovedList = new ArrayList<>();
        this.imageModelArrayList = GetSelectedData();
        this.ctx = ctx;
        mLocalService = ((LocalApiService) ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
    }

    @Override
    public AddUserAllergiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_add_allrgies_rv, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(final AddUserAllergiesAdapter.MyViewHolder holder, int position) {

        //holder.checkBox.setText("Checkbox " + position);
        holder.checkBox.setChecked(imageModelArrayList.get(position).getSelected());
        holder.tvAnimal.setText(imageModelArrayList.get(position).getAllergyName());

        // holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);

        manageClick(holder.checkBox, position);
    }


    public void manageClick(CheckBox checkBox, int pos) {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //    Integer pos = (Integer) holder.checkBox.getTag();
                //     Toast.makeText(ctx, imageModelArrayList.get(pos).getAllergyName() + " clicked!", Toast.LENGTH_SHORT).show();

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        if (imageModelArrayList.get(pos).getSelected()) {
                            imageModelArrayList.get(pos).setSelected(false);
                            realm.insertOrUpdate(imageModelArrayList.get(pos));

                        } else {
                            imageModelArrayList.get(pos).setSelected(true);
                            realm.insertOrUpdate(imageModelArrayList.get(pos));
                        }


                    }
                });

                if (!imageModelArrayList.get(pos).getSelected()) {
                    getUserAlergyIDRemovedList(imageModelArrayList.get(pos),imageModelArrayList.get(pos).getAllergyId());
                    //getUserAlergyIDRemovedList(newUserAlergyArrayList.get(pos).getAllergyId());
                    newUserAlergyArrayList.remove(imageModelArrayList.get(pos));
                    imageModelArrayList.remove(pos);
                    //newUserAlergyArrayList.remove(pos);
                    notifyDataSetChanged();

                }


           }
        });


    }


    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        protected CheckBox checkBox;
        private TextView tvAnimal, UserSpesified;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            tvAnimal = (TextView) itemView.findViewById(R.id.animal);
          //UserSpesified = (TextView) itemView.findViewById(R.id.userSpecified);
        }

    }


    public ArrayList<String> getUserAlergyNameList() {
        ArrayList<String> alergyIDList = new ArrayList<>();


        for(int i=0;i<newUserAlergyArrayList.size();i++){
            String alergyName = newUserAlergyArrayList.get(i).getAllergyName();
            alergyIDList.add(alergyName);

        }


        return alergyIDList;
    }

    public void getUserAlergyIDRemovedList(UserAlergies userAlergies,Integer id){


        if(!userAlergies.getRecentlySelected()){
            alergyUserIDRemovedList.add(String.valueOf(id));
        }



    }


    public void AddItem(String AllergiName) {

/*        final UserAlergies AllergResults= mLocalService.where(UserAlergies.class).equalTo("allergyName",AllergiName)
                .findFirst();*/


        UserAlergies userAlergies = new UserAlergies();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                userAlergies.setAllergyName(String.valueOf(AllergiName));
                userAlergies.setSelected(true);
                userAlergies.setRecentlySelected(true);
                realm.insertOrUpdate(userAlergies);


            }
        });


        imageModelArrayList.add(userAlergies);
        newUserAlergyArrayList.add(userAlergies);
        notifyDataSetChanged();
    }





    public List<UserAlergies> GetSelectedData(){
        List<UserAlergies> allergiesDetailsRealmList = new ArrayList<>();
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        final RealmResults<UserAlergies> AllergiesDetailsRealmResults= mLocalService.where(UserAlergies.class).equalTo("selected",true)
                .findAll();
        if(AllergiesDetailsRealmResults !=null){

            for(int i=0; i<AllergiesDetailsRealmResults.size(); i++)
                allergiesDetailsRealmList.add(AllergiesDetailsRealmResults.get(i));
        }

        return allergiesDetailsRealmList;
    }


}
