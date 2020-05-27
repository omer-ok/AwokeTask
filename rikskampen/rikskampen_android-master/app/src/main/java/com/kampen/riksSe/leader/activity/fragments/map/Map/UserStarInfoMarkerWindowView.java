package com.kampen.riksSe.leader.activity.fragments.map.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.kampen.riksSe.R;

public class UserStarInfoMarkerWindowView implements GoogleMap.InfoWindowAdapter{
    private Context context;

    public UserStarInfoMarkerWindowView(Context ctx){
        context = ctx;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.dialog_box_start_chase, null);

        //view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //view.setBackground(Transpa);
        try{


            String StarNum = (String) marker.getTag();



            final TextView startDialog = view.findViewById(R.id.startDialog);
            final TextView startDialog1 = view.findViewById(R.id.startDialog1);




            final SpannableStringBuilder sb = new SpannableStringBuilder("Du har valt "+StarNum.concat(" KM"));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder("avstånd för att uppnå "+StarNum.concat(" stjärnor"));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 12, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss,22,32, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


            startDialog.setText(sb);
            startDialog1.setText(sb1);

        }catch (Exception e){

        }
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) { // 2
        //User user = (User) marker.getTag();  // 3
        //if (user == null) return clusterItemView;
        /*View view = ((ActivityDaily)context).getLayoutInflater()
                .inflate(R.layout.dialog_box_start_chase, null);

        //view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //view.setBackground(Transpa);
        try{


        String StarNum = (String) marker.getTag();



        final TextView startDialog = view.findViewById(R.id.startDialog);
        final TextView startDialog1 = view.findViewById(R.id.startDialog1);




        final SpannableStringBuilder sb = new SpannableStringBuilder("Du har valt "+StarNum.concat(" KM"));
        final SpannableStringBuilder sb1 = new SpannableStringBuilder("avstånd för att uppnå "+StarNum.concat(" stjärnor"));
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(bss, 12, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb1.setSpan(bss,22,32, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        startDialog.setText(sb);
        startDialog1.setText(sb1);
        }catch (Exception e){

        }*/
        return null;  // 4
    }


}
