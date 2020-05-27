package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantWeeklySectionsSchduleAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ModelList.MonthWeekSchduleList;

import java.util.ArrayList;
import java.util.List;

public class ContestantReschduleLiveVideoCallActivity extends AppCompatActivity {

    RecyclerView mMonthWeeklyRV;
    ContestantWeeklySectionsSchduleAdapter mContestantWeeklySectionsSchduleAdapter;
    Context mContext;
    LinearLayoutManager mLayoutManagerWeek1DaysSchdule;

    List<MonthWeekSchduleList> monthWeekSchduleLists;
    List<String> days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_reschdule_live_video_call);

        mContext = ContestantReschduleLiveVideoCallActivity.this;

        mMonthWeeklyRV = findViewById(R.id.ScheduledLiveVideoCallMonthWeelyRV);

        mLayoutManagerWeek1DaysSchdule = new GridLayoutManager(mContext, 1);
        mLayoutManagerWeek1DaysSchdule.setOrientation(GridLayoutManager.VERTICAL);
        //mLayoutManagerWeek1DaysSchdule = new LinearLayoutManager(mContext);

        monthWeekSchduleLists = new ArrayList();
        days = new ArrayList();
        days.add("Mon");
        days.add("Tus");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");

        MonthWeekSchduleList monthWeekSchduleList1 =new MonthWeekSchduleList();
        MonthWeekSchduleList monthWeekSchduleList2 =new MonthWeekSchduleList();
        MonthWeekSchduleList monthWeekSchduleList3 =new MonthWeekSchduleList();
        MonthWeekSchduleList monthWeekSchduleList4 =new MonthWeekSchduleList();

        monthWeekSchduleList1.setWeekName("Week 1");
        monthWeekSchduleList1.setDayName(days);

        monthWeekSchduleList2.setWeekName("Week 2");
        monthWeekSchduleList2.setDayName(days);

        monthWeekSchduleList3.setWeekName("Week 3");
        monthWeekSchduleList3.setDayName(days);

        monthWeekSchduleList4.setWeekName("Week 4");
        monthWeekSchduleList4.setDayName(days);

        monthWeekSchduleLists.add(monthWeekSchduleList1);
        monthWeekSchduleLists.add(monthWeekSchduleList2);
        monthWeekSchduleLists.add(monthWeekSchduleList3);
        monthWeekSchduleLists.add(monthWeekSchduleList4);


        mContestantWeeklySectionsSchduleAdapter = new ContestantWeeklySectionsSchduleAdapter(mContext,monthWeekSchduleLists);


       /* List<SectionedGridRecyclerViewAdapter.Section> sections = new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

        for(int j=0;j<monthWeekSchduleLists.size();j++){
            sections.add(new SectionedGridRecyclerViewAdapter.Section(j, monthWeekSchduleLists.get(j).getWeekName()));

        }*/
        mMonthWeeklyRV.setLayoutManager(mLayoutManagerWeek1DaysSchdule);
        //Add your adapter to the sectionAdapter
       /* SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(mContext,R.layout.schdule_headers,R.id.Header,mMonthWeeklyRV,mContestantWeeklySectionsSchduleAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));*/


        mMonthWeeklyRV.setAdapter(mContestantWeeklySectionsSchduleAdapter);

    }

    @Override
    public void onBackPressed() {

        finish();
    }

    public void onBackClick(View view) {

        finish();
    }
}
