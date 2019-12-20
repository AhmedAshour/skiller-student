package com.codevenue.skillerandroid.views.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.trending.TrendingTutorsRecyclerAdapter;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrendingFragment extends Fragment {

    @BindView(R.id.rv_trending_tutors) RecyclerView rvTrendingTutors;

    TrendingTutorsRecyclerAdapter trendingTutorsRecyclerAdapter;
    TutorsViewModel tutorsViewModel;
    List<Tutor> tutorsTopTenList;
    public TrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trending, container, false);
        ButterKnife.bind(this, v);

        initRvTrendingTutors(v);
        populatingTrendingTutorsRv(v);

        return v;
    }

    private void initRvTrendingTutors(View v) {
        rvTrendingTutors.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTrendingTutors.setHasFixedSize(true);

        trendingTutorsRecyclerAdapter = new TrendingTutorsRecyclerAdapter();
        rvTrendingTutors.setAdapter(trendingTutorsRecyclerAdapter);

    }
    private void populatingTrendingTutorsRv(View v) {
        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        tutorsViewModel.getTopTenTutors().observe(this, new Observer<List<Tutor>>() {
            @Override
            public void onChanged(@Nullable List<Tutor> tutors) {
                //tutorsTopTenList = tutors;
                trendingTutorsRecyclerAdapter = new TrendingTutorsRecyclerAdapter(tutors, getActivity());
                rvTrendingTutors.setAdapter(trendingTutorsRecyclerAdapter);
            }
        });
    }

}
