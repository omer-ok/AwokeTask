package com.kampen.riksSe.leader.activity.fragments.home.nutrition.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.RecipeVideoActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter.DetailNutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class PageControlerAdapter extends RecyclerView.Adapter<PageControlerAdapter.ViewHolder> {

    private List<Recipe> MealOptionArrayList;
    private Context context;
    DetailNutritionAdapter ingrdientsAdapter;
    LinearLayoutManager mLayoutManager1;
    int MealID,MealIngrediantID;

    public PageControlerAdapter(Context context,int MealID,int MealIngrediantID,List<Recipe> MealOptionArrayList) {
        this.MealOptionArrayList = MealOptionArrayList;
        this.context = context;
        this.MealID = MealID;
        this.MealIngrediantID = MealIngrediantID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if(Locale.getDefault().getLanguage().equals("en")){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.nutrition_meal_item, viewGroup, false);
            return new ViewHolder(v);
        }
        if(Locale.getDefault().getLanguage().equals("sv")){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.nutrition_meal_item_sv, viewGroup, false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //if(MealName.equals(MealOptionArrayList.get(position).getMealName())){


        try{

            loadImage(holder.mealImage,MealOptionArrayList.get(position).getmMediaImage());
            holder.mealtitle.setText(MealOptionArrayList.get(position).getmName());
            holder.mealDescription.setText(MealOptionArrayList.get(position).getmDescription());
            holder.mealDescription.setMovementMethod(new ScrollingMovementMethod());
            mLayoutManager1 = new LinearLayoutManager(context);
            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
            ingrdientsAdapter =new DetailNutritionAdapter(context,MealOptionArrayList.get(position).getmIngredients());

            holder.ingrediantsView.setLayoutManager(mLayoutManager1);

            holder.ingrediantsView.setAdapter(ingrdientsAdapter);

            if(MealOptionArrayList.get(position).getmMediaVideo()!=null){
                holder.recipeBtn.setVisibility(View.VISIBLE);
            }else{
                holder.recipeBtn.setVisibility(View.GONE);
            }

        }catch (Exception e){

            Log.i("GlideEX",e.toString());
        }


            holder.recipeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, RecipeVideoActivity.class);
                    intent.putExtra("RecipeID",MealOptionArrayList.get(position).getmId());
                    intent.putExtra("MealID",MealID);
                    intent.putExtra("MealIngrediantID",MealIngrediantID);
                    context.startActivity(intent);
                }
            });

       // }

    }

    @Override
    public int getItemCount() {
        return MealOptionArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ImageView mealImage;
    public TextView mealDescription,mealtitle;
    public Button recipeBtn;
    public RecyclerView ingrediantsView;

    public ViewHolder(View v) {
        super(v);

        mealtitle =v.findViewById(R.id.title);
        mealImage =v.findViewById(R.id.nutrition_img);
        mealDescription = v.findViewById(R.id.descriptionValue);
        recipeBtn = v.findViewById(R.id.recipeMore);
        ingrediantsView = v.findViewById(R.id.IngrediantsList);
        view=v;
    }


}

    private void loadImage(final ImageView imageView, final String imageUrl){


        GlideApp
                .with(context)
                .load(imageUrl)
                .error(R.drawable.icon_nutrition_empty_img)
                .into(imageView);

          /* Picasso.get()
                .load(imageUrl)

                .into(imageView , new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });*/
    }

}
