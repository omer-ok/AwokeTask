package com.kampen.riksSe.leader.activity.fragments.order_history.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.adapters.CancelOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CancelOrderFragment extends Fragment {


    private RecyclerView mCancelOrderRecyclerView;
    private CancelOrderAdapter mCancelOrderAdapter;


    public static CancelOrderFragment newInstance() {
        CancelOrderFragment fragment = new CancelOrderFragment();
        return fragment;
    }


    public CancelOrderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_canceled_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCancelOrderRecyclerView = (RecyclerView) view.findViewById(R.id.canceledOrderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mCancelOrderAdapter=new CancelOrderAdapter();


        mCancelOrderRecyclerView.setLayoutManager(mLayoutManager1);
        mCancelOrderRecyclerView.setAdapter(mCancelOrderAdapter);
    }
}
