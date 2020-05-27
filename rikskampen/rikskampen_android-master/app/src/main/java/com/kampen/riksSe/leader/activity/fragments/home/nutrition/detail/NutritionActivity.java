package com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.RecyclerItemClickListener;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter.DetailNutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;

import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity {


    protected final String[] parties = new String[] {
            "Protein", "Fats", "Calories", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private static final int PERMISSION_STORAGE = 0;

    protected Typeface tfRegular;
    protected Typeface tfLight;


    private PieChart chart;


    private RecyclerView  mNutritionDetailRV;

    private DetailNutritionAdapter mDetailNutritionItem;

    private Context   mContext;
    public TextView Title,ingredientsSorry,recipeName;
    float Calories ,Protine  ,Fats;
    String title,RecipeName,Description;

    String RecipeVideoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);






        int weekId=getIntent().getIntExtra("selected_week",1);
        int dayId=getIntent().getIntExtra("selected_day",1);
        int selected_id=getIntent().getIntExtra("selected_video",1);
        title =getIntent().getStringExtra("Title");
        RecipeName =getIntent().getStringExtra("RecipeName");

        Title = findViewById(R.id.mealtitle);

        Title.setText(RecipeName);



        ingredientsSorry =findViewById(R.id.Ingredients);

        mNutritionDetailRV=findViewById(R.id.nutritionDetailRV);

        chart = findViewById(R.id.chartNutritionDetail);

        recipeName = findViewById(R.id.recipename);

        recipeName.setText(title);

        DayNutritionDB dailyIngrediants = Repository.getIngrediantsDetail(weekId,dayId,selected_id);

        RecipeVideoURL = dailyIngrediants.getRecipesMedia();
        Description = dailyIngrediants.getDescription();

        if(dailyIngrediants!=null  && dailyIngrediants.getIngredientsArrayList().size() > 0 ){

            LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
            mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

           // mDetailNutritionItem=new DetailNutritionAdapter(mContext,dailyIngrediants.getIngredientsArrayList());

            mNutritionDetailRV.setLayoutManager(mLayoutManager1);

            mNutritionDetailRV.setAdapter(mDetailNutritionItem);

            for(int i=0; i<dailyIngrediants.getIngredientsArrayList().size();i++){

                Calories+= Float.parseFloat(dailyIngrediants.getIngredientsArrayList().get(i).getCalories());
                Protine+= Float.parseFloat(dailyIngrediants.getIngredientsArrayList().get(i).getProtein());
                Fats+= Float.parseFloat(dailyIngrediants.getIngredientsArrayList().get(i).getFats());

            }

            init();

        }
        else{

            chart.setVisibility(View.GONE);
            mNutritionDetailRV.setVisibility(View.GONE);
            ingredientsSorry.setVisibility(View.VISIBLE);

        }

        mNutritionDetailRV.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mNutritionDetailRV ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        /*Calories=dailyIngrediants.getIngredientsArrayList().get(position).getCalories();
                        Protine=dailyIngrediants.getIngredientsArrayList().get(position).getProtein();
                        Fats=dailyIngrediants.getIngredientsArrayList().get(position).getFats();*/


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever


                    }
                })
        );


    }


    private  void init()
    {

        mContext=NutritionActivity.this;


        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");


        mNutritionDetailRV = findViewById(R.id.nutritionDetailRV);



        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfLight);
        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.parseColor("#F25022"));
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);


        chart.postDelayed(new Runnable() {
            @Override
            public void run() {

                setData(3, 10);
            }
        },200 );
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Rikskampen"+"\n"+RecipeName+"\n"+ "calories chart ");
       /*
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 10, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 13, s.length() - 5, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 13, s.length() - 5, 0);*/

        s.setSpan(new RelativeSizeSpan(1f), 10, s.length() - 5, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), 0 , s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0 , s.length(), 0);
        return s;
    }


    private void setData(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
       /* for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.drawable.ic_about)));
        }*/




        PieEntry pieEntry=new PieEntry(1);


      //  pieEntry.setData();

           // entries.add(new PieEntry( (float) Float.valueOf(Calories),Float.valueOf(Protine),Float.valueOf(Fats)));
        entries.add(new PieEntry((float) Calories,"Calories"));
        entries.add(new PieEntry((float) Fats,"Fats"));
        entries.add(new PieEntry((float) Protine,"Protines"));

        if(Calories !=0 && Fats != 0 && Protine != 0 ){



        }


        PieDataSet dataSet = new PieDataSet(entries, "Total Values");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.RED);
        //data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();


    }

    public void onRecipeClick(View view) {

        if(RecipeVideoURL.isEmpty()){

            /*Intent intent = new Intent(mContext, RecipeVideoActivity.class);
            intent.putExtra("RecipeVideo","No URL");

           startActivity(intent);*/
        }
        else{
            Intent intent = new Intent(mContext, RecipeVideoActivity.class);
            intent.putExtra("RecipeVideo",RecipeVideoURL);
            intent.putExtra("title",title);
            intent.putExtra("Description",Description);
            startActivity(intent);
//            Toast(get).show;
        }



    }


    public void onBackClick(View view) {

        finish();
    }
}
