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
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddAllergiesAdapter extends RecyclerView.Adapter<AddAllergiesAdapter.MyViewHolder>{


    private LayoutInflater inflater;
    public  List<Alergy> imageModelArrayList;
    public  List<Alergy> newAlergyArrayList;
    private Context ctx;
    private Realm mLocalService;
    private AlergyRemoveListner alergyRemoveListner;

   public ArrayList<String> alergyIDRemovedList;

    public AddAllergiesAdapter(Context ctx) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = GetSelectedData();
        this.newAlergyArrayList = new ArrayList<>();
        this.alergyIDRemovedList = new ArrayList<>();
        this.ctx = ctx;
         mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
    }

    @Override
    public AddAllergiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_add_allrgies_rv, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AddAllergiesAdapter.MyViewHolder holder, int position) {

        //holder.checkBox.setText("Checkbox " + position);
        holder.checkBox.setChecked(imageModelArrayList.get(position).getSelected());
        holder.tvAnimal.setText(imageModelArrayList.get(position).getAllergyName());

        // holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);

        manageClick(holder.checkBox,position);
    }



    public void manageClick(CheckBox checkBox , int pos){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

                if(!imageModelArrayList.get(pos).getSelected()){
                    if(alergyRemoveListner != null){
                         alergyRemoveListner.OnAlergyRemoved(imageModelArrayList.get(pos));
                    }
                    getAlergyIDRemovedList(imageModelArrayList.get(pos),imageModelArrayList.get(pos).getAllergyId());
                    //getAlergyIDRemovedList(newAlergyArrayList.get(pos).getAllergyId());
                    newAlergyArrayList.remove(imageModelArrayList.get(pos));
                    imageModelArrayList.remove(pos);
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
        private TextView tvAnimal,UserSpesified;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            tvAnimal = (TextView) itemView.findViewById(R.id.animal);
          //  UserSpesified = (TextView) itemView.findViewById(R.id.userSpecified);
        }

    }

    public ArrayList<String> getAlergyIDList() {
        ArrayList<String> alergyIDList = new ArrayList<>();


        for(int i=0;i<newAlergyArrayList.size();i++){
            Integer alergyID = newAlergyArrayList.get(i).getAllergyId();
            alergyIDList.add(String.valueOf(alergyID));

        }


        return alergyIDList;
    }

    public void getAlergyIDRemovedList(Alergy alergy,Integer id){

        if(!alergy.getRecentlySelected()){
            alergyIDRemovedList.add(String.valueOf(id));
        }
    }

    public void AddItem(String AllergiName){

        final Alergy AllergResults= mLocalService.where(Alergy.class).equalTo("allergyName",AllergiName)
                .findFirst();



        if(AllergResults !=null){

            imageModelArrayList.add(AllergResults);
            newAlergyArrayList.add(AllergResults);
            mLocalService.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    AllergResults.setSelected(true);
                    AllergResults.setRecentlySelected(true);
                    realm.insertOrUpdate(AllergResults);



                }
            });

            notifyDataSetChanged();
        }



    }

    public List<Alergy> GetSelectedData(){
        List<Alergy> allergiesDetailsRealmList = new ArrayList<>();
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        final RealmResults<Alergy> AllergiesDetailsRealmResults= mLocalService.where(Alergy.class).equalTo("selected",true)
                .findAll();


        if(AllergiesDetailsRealmResults !=null){

            for(int i=0; i<AllergiesDetailsRealmResults.size(); i++)
                allergiesDetailsRealmList.add(AllergiesDetailsRealmResults.get(i)) ;
        }

        return allergiesDetailsRealmList;
    }
    public void SetAlergyRemovedListner(AlergyRemoveListner alergyRemoveListner){

        this.alergyRemoveListner =alergyRemoveListner;
    }

    public interface AlergyRemoveListner{
        public void OnAlergyRemoved(Alergy alergy);
    }

}
