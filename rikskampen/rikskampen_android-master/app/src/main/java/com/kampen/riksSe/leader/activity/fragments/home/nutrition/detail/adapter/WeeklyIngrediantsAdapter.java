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
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.WeeklyIngredients;

import java.util.ArrayList;
import java.util.Random;

import io.realm.RealmList;


public class WeeklyIngrediantsAdapter extends RecyclerView.Adapter<WeeklyIngrediantsAdapter.ViewHolder>{



    ArrayList<TakeItem> mDailyVideoArrayList;
    RealmList<WeeklyIngredients> mingredientsArrayList;
    private Context mContext;

    int weekid;

    public WeeklyIngrediantsAdapter(Context context, RealmList<WeeklyIngredients> ingredientsArrayList)
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
                .inflate(R.layout.item_weekly_ingredients, viewGroup, false);

        return new WeeklyIngrediantsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        try {



            viewHolder.headingTV.setText(mingredientsArrayList.get(i).getIngredientName());

            viewHolder.qtyTV.setText(mingredientsArrayList.get(i).getUnit());



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

        public TextView headingTV,qtyTV,caloriesValue,protineValue,fatsValue;

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
