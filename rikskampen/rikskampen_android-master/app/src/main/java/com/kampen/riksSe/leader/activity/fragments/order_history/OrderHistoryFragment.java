package com.kampen.riksSe.leader.activity.fragments.order_history;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.CancelOrderFragment;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.CompletedOrderFragment;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.PendingOrderFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class OrderHistoryFragment extends Fragment {


    private ViewPager mViewPager;
    private OrderPagerAdapter orderPagerAdapter;


    private TextView tabLeft,tabMiddle,tabRight;

    public  static int selectedTab=0;


    private final String[] PAGE_TITLES = new String[]{

            "Pending",
            "Completed",
            "Canceled"

    };

    public Fragment[] getFragments() {


        Fragment[] PAGES = new Fragment[]{

                PendingOrderFragment.newInstance(),
                CompletedOrderFragment.newInstance(),
                CancelOrderFragment.newInstance(),

        };

        return PAGES;
    }


    public OrderHistoryFragment() {

    }

    public static OrderHistoryFragment newInstance() {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tabLeft=view.findViewById(R.id.tabLeft);
        tabMiddle=view.findViewById(R.id.tabMiddle);
        tabRight=view.findViewById(R.id.tabRight);



        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        orderPagerAdapter=new OrderPagerAdapter(getChildFragmentManager(),getFragments());
        mViewPager.setAdapter(orderPagerAdapter);

        /*TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);*/

        manageTab();

    }



    private  void manageTab()
    {

        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });


        if(selectedTab==0)
        {
            tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
            tabLeft.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));
        }
        else if(selectedTab==1)
        {
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_selected);
            tabMiddle.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
        }
        else
        {
            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            tabRight.setBackgroundResource(R.drawable.tab_right_selected);
            tabRight.setTextColor(Color.WHITE);
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));
        }

        tabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
                tabLeft.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));

                mViewPager.setCurrentItem(0);

                selectedTab=0;

            }
        });


        tabMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabMiddle.setBackgroundResource(R.drawable.tab_middle_selected);
                tabMiddle.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));

                mViewPager.setCurrentItem(1);

                selectedTab=1;

            }
        });


        tabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));
                tabRight.setBackgroundResource(R.drawable.tab_right_selected);
                tabRight.setTextColor(Color.WHITE);
                tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));
                mViewPager.setCurrentItem(2);
                selectedTab=2;

            }
        });




    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public class OrderPagerAdapter extends FragmentPagerAdapter {

        Fragment[] PAGES;

        public OrderPagerAdapter(FragmentManager fragmentManager, Fragment[] PAGES) {

            super(fragmentManager);

            this.PAGES=PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            return PAGES[position];
        }

        @Override
        public int getCount() {

            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

}
