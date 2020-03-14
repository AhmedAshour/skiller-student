package me.ahmedashour.skiller.views.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.trending.TrendingTutorsRecyclerAdapter;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.viewmodels.TutorsViewModel;


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
        tutorsViewModel.getTopTenTutors().observe(getViewLifecycleOwner(), new Observer<List<Tutor>>() {
            @Override
            public void onChanged(@Nullable List<Tutor> tutors) {
                //tutorsTopTenList = tutors;
                trendingTutorsRecyclerAdapter = new TrendingTutorsRecyclerAdapter(tutors, getActivity());
                rvTrendingTutors.setAdapter(trendingTutorsRecyclerAdapter);
            }
        });
    }

}
