package com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.model.TakeItem;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Ingredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.IngredientsDB;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import io.realm.RealmList;


public class DetailNutritionAdapter extends RecyclerView.Adapter<DetailNutritionAdapter.ViewHolder>{



    ArrayList<TakeItem> mDailyVideoArrayList;
    RealmList<Ingredient> mingredientsArrayList;
    private Context mContext;

    int weekid;

    public DetailNutritionAdapter(Context context,RealmList<Ingredient> ingredientsArrayList)
    {
        mDailyVideoArrayList =generateDummyData();
        this.mingredientsArrayList=ingredientsArrayList;
        mContext=context;
        this.weekid=weekid;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ingredients, viewGroup, false);

        return new DetailNutritionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        try {

            if(Locale.getDefault().getLanguage().equals("en")){
                if(mingredientsArrayList.get(i).getmIngredientName().equals("0")){
                    viewHolder.headingTV.setText("0");
                }else{
                    viewHolder.headingTV.setText(mingredientsArrayList.get(i).getmIngredientName());
                }
                if(mingredientsArrayList.get(i).getmCalories()<1){
                    viewHolder.caloriesValue.setText("0");
                }else{
                    String mCalories = String.valueOf(mingredientsArrayList.get(i).getmCalories());
                    if(mCalories.contains(".0")){
                        int mCaloriesInt = (int) mingredientsArrayList.get(i).getmCalories();
                        viewHolder.caloriesValue.setText(mCaloriesInt+"");
                    }else{
                        viewHolder.caloriesValue.setText(mingredientsArrayList.get(i).getmCalories()+"");
                    }
                }
                if(mingredientsArrayList.get(i).getmFats()<1){
                    viewHolder.fatsValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmFats_Unit()+""));
                }else{
                    String mFats = String.valueOf(mingredientsArrayList.get(i).getmFats());
                    if(mFats.contains(".0")){
                        int mFatsInt = (int) mingredientsArrayList.get(i).getmFats();
                        viewHolder.fatsValue.setText(mFatsInt+" ".concat(mingredientsArrayList.get(i).getmFats_Unit()+""));
                    }else{
                        viewHolder.fatsValue.setText(mingredientsArrayList.get(i).getmFats()+" ".concat(mingredientsArrayList.get(i).getmFats_Unit()+""));
                    }
                }
                if(mingredientsArrayList.get(i).getmProtein()<1){
                    viewHolder.protineValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit()+""));
                }else{
                    String mProtine = String.valueOf(mingredientsArrayList.get(i).getmProtein());
                    if(mProtine.contains(".0")){
                        int mProtineInt = (int) mingredientsArrayList.get(i).getmProtein();
                        viewHolder.protineValue.setText(mProtineInt+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit()+""));
                    }else{
                        viewHolder.protineValue.setText(mingredientsArrayList.get(i).getmProtein()+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit()+""));
                    }
                }
                if(mingredientsArrayList.get(i).getmQuantity()<1){
                    viewHolder.qtyTV.setText("0");
                }else{
                    String mQuantity = String.valueOf(mingredientsArrayList.get(i).getmQuantity());
                    if(mQuantity.contains(".0")){
                        int mQuantityInt = (int) mingredientsArrayList.get(i).getmQuantity();
                        viewHolder.qtyTV.setText(mQuantityInt+"".concat(" ").concat(mingredientsArrayList.get(i).getmUnit()));
                    }else{
                        viewHolder.qtyTV.setText(mingredientsArrayList.get(i).getmQuantity()+"".concat(" ").concat(mingredientsArrayList.get(i).getmUnit()));
                    }


                }
                if(mingredientsArrayList.get(i).getmCarbs()<1){
                    viewHolder.carbsValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit()+""));
                }else{
                    String mCarbs = String.valueOf(mingredientsArrayList.get(i).getmCarbs());
                    if(mCarbs.contains(".0")){
                        int mCarbsInt = (int) mingredientsArrayList.get(i).getmCarbs();
                        viewHolder.carbsValue.setText(mCarbsInt+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit()+""));
                    }else{
                        viewHolder.carbsValue.setText(mingredientsArrayList.get(i).getmCarbs()+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit()+""));
                    }
                }
            }

            if(Locale.getDefault().getLanguage().equals("sv")){
                if(mingredientsArrayList.get(i).getmIngredientNameSv().equals("0")){
                    viewHolder.headingTV.setText("0");
                }else{
                    viewHolder.headingTV.setText(mingredientsArrayList.get(i).getmIngredientNameSv());
                }
                if(mingredientsArrayList.get(i).getmCalories()<1){
                    viewHolder.caloriesValue.setText("0");
                }else{
                    String mCalories = String.valueOf(mingredientsArrayList.get(i).getmCalories());
                    if(mCalories.contains(".0")){
                        int mCaloriesInt = (int) mingredientsArrayList.get(i).getmCalories();
                        viewHolder.caloriesValue.setText(mCaloriesInt+"");
                    }else{
                        viewHolder.caloriesValue.setText(mingredientsArrayList.get(i).getmCalories()+"");
                    }
                }
                if(mingredientsArrayList.get(i).getmFats()<1){
                    viewHolder.fatsValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmFats_Unit_Sv()+""));
                }else{
                    String mFats = String.valueOf(mingredientsArrayList.get(i).getmFats());
                    if(mFats.contains(".0")){
                        int mFatsInt = (int) mingredientsArrayList.get(i).getmFats();
                        viewHolder.fatsValue.setText(mFatsInt+" ".concat(mingredientsArrayList.get(i).getmFats_Unit_Sv()+""));
                    }else{
                        viewHolder.fatsValue.setText(mingredientsArrayList.get(i).getmFats()+" ".concat(mingredientsArrayList.get(i).getmFats_Unit_Sv()+""));
                    }
                }
                if(mingredientsArrayList.get(i).getmProtein()<1){
                    viewHolder.protineValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit_Sv()+""));
                }else{
                    String mProtine = String.valueOf(mingredientsArrayList.get(i).getmProtein());
                    if(mProtine.contains(".0")){
                        int mProtineInt = (int) mingredientsArrayList.get(i).getmProtein();
                        viewHolder.protineValue.setText(mProtineInt+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit_Sv()+""));
                    }else{
                        viewHolder.protineValue.setText(mingredientsArrayList.get(i).getmProtein()+" ".concat(mingredientsArrayList.get(i).getmProtein_Unit_Sv()+""));
                    }
                }
                if(mingredientsArrayList.get(i).getmQuantity()<1){
                    viewHolder.qtyTV.setText("0");
                }else{
                    String mQuantity = String.valueOf(mingredientsArrayList.get(i).getmQuantity());
                    if(mQuantity.contains(".0")){
                        int mQuantityInt = (int) mingredientsArrayList.get(i).getmQuantity();
                        viewHolder.qtyTV.setText(mQuantityInt+"".concat(" ").concat(mingredientsArrayList.get(i).getmUnit_Sv()));
                    }else{
                        viewHolder.qtyTV.setText(mingredientsArrayList.get(i).getmQuantity()+"".concat(" ").concat(mingredientsArrayList.get(i).getmUnit_Sv()));
                    }


                }
                if(mingredientsArrayList.get(i).getmCarbs()<1){
                    viewHolder.carbsValue.setText("0"+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit_Sv()+""));
                }else{
                    String mCarbs = String.valueOf(mingredientsArrayList.get(i).getmCarbs());
                    if(mCarbs.contains(".0")){
                        int mCarbsInt = (int) mingredientsArrayList.get(i).getmCarbs();
                        viewHolder.carbsValue.setText(mCarbsInt+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit_Sv()+""));
                    }else{
                        viewHolder.carbsValue.setText(mingredientsArrayList.get(i).getmCarbs()+" ".concat(mingredientsArrayList.get(i).getmCarbs_Unit_Sv()+""));
                    }
                }
            }




        }catch (Exception ex)
        {
                 ex.toString();
        }


    }

    @Override
    public int getItemCount() {


        return mingredientsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headingTV,qtyTV,caloriesValue,protineValue,fatsValue,carbsValue;

        public ImageView thumbNail;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.IngredientName);
            qtyTV=v.findViewById(R.id.UnitValue);
            //thumbNail=v.findViewById(R.id.itemThumbNail);
            caloriesValue=v.findViewById(R.id.CaloriesValue);
            protineValue=v.findViewById(R.id.ProtineValue);
            fatsValue=v.findViewById(R.id.FatsValue);
            carbsValue=v.findViewById(R.id.CarbsValue);
            view=v;

        }


    }




    private   ArrayList<TakeItem> generateDummyData()
    {
        ArrayList<TakeItem> hmArray=new ArrayList<>();


        String [][] habitNames=new String[4][2];

        habitNames[0][0]="Simple Spanish Scramble";
        habitNames[1][0]="Tropical Fruit Smoothie";
        habitNames[2][0]="Simple Yolk Scramble";
        habitNames[3][0]="Tropical Juice Smoothie";


        habitNames[0][1]=(R.drawable.ic_patatoes)+"";
        habitNames[1][1]=(R.drawable.ic_blueberries)+"";
        habitNames[2][1]=(R.drawable.ic_eggs_yolk)+"";
        habitNames[3][1]=(R.drawable.ic_liver)+"";


        Random  random=new Random();

        for(int i=0; i<habitNames.length; i++)
        {
            TakeItem hm=new TakeItem();
            hm.setId(i);

            hm.setTitle(habitNames[i][0]);
            hm.setTempPath(Integer.parseInt(habitNames[i][1]));

            hm.setQty(1);
            hm.setProtein(40);
            int cal=100+random.nextInt(1000);
            hm.setCalories(cal);
            hm.setFats(45);
            hmArray.add(hm);
        }


        return  hmArray;
    }
}
