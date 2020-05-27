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
import com.kampen.riksSe.leader.activity.fragments.order_history.fragments.adapters.CompletedOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrderFragment extends Fragment {


    private RecyclerView mCompletedOrderRecyclerView;
    private CompletedOrderAdapter mCompletedOrderAdapter;





    public static CompletedOrderFragment newInstance() {
        CompletedOrderFragment fragment = new CompletedOrderFragment();
        return fragment;
    }


    public CompletedOrderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_completed_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCompletedOrderRecyclerView = (RecyclerView) view.findViewById(R.id.completedOrderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mCompletedOrderAdapter=new CompletedOrderAdapter();


        mCompletedOrderRecyclerView.setLayoutManager(mLayoutManager1);
        mCompletedOrderRecyclerView.setAdapter(mCompletedOrderAdapter);
    }
}
