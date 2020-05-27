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
import com.kampen.riksSe.leader.activity.fragments.map.Modal.StarPickInfoWindowModel;

public class UserStarPickedInfoMarkerWindowView implements GoogleMap.InfoWindowAdapter {


    private Context context;

    public UserStarPickedInfoMarkerWindowView(Context ctx) {
        context = ctx;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.dialog_box_star_picked, null);

        try {

            StarPickInfoWindowModel starPickInfoWindowModel = (StarPickInfoWindowModel) marker.getTag();


            final TextView starNumber = view.findViewById(R.id.starNumber);
            final TextView starTxtDialog = view.findViewById(R.id.starDialog1);
            final TextView startTxtDialog1 = view.findViewById(R.id.starDialog2);

            final SpannableStringBuilder sb = new SpannableStringBuilder(starPickInfoWindowModel.getCurrentPickedStar().concat(" stjärnorna är kvar"));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder("på din valda ".concat(String.valueOf(starPickInfoWindowModel.getMstarClicked())).concat(" KM väg"));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 13, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(starPickInfoWindowModel.getOldStarPicked());
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path

        }catch (Exception e){}

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {

        /*View view = ((ActivityDaily)context).getLayoutInflater()
                .inflate(R.layout.dialog_box_star_picked, null);

        try {

            StarPickInfoWindowModel starPickInfoWindowModel = (StarPickInfoWindowModel) marker.getTag();


            final TextView starNumber = view.findViewById(R.id.starNumber);
            final TextView starTxtDialog = view.findViewById(R.id.starDialog1);
            final TextView startTxtDialog1 = view.findViewById(R.id.starDialog2);

            final SpannableStringBuilder sb = new SpannableStringBuilder(starPickInfoWindowModel.getCurrentPickedStar().concat(" stjärnorna är kvar"));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder("på din valda ".concat(String.valueOf(starPickInfoWindowModel.getMstarClicked())).concat(" KM väg"));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 13, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(starPickInfoWindowModel.getOldStarPicked());
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path

        }catch (Exception e){}*/

        return null;
    }

}
