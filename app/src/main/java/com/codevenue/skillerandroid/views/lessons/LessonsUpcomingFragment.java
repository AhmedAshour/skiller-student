package com.codevenue.skillerandroid.views.lessons;


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
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.lessons.LessonsRequestsRecyclerAdapter;
import com.codevenue.skillerandroid.adapters.lessons.LessonsUpcomingRecyclerAdapter;
import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewmodels.LessonsViewModel;
import com.codevenue.skillerandroid.viewmodels.lessons.LessonsUpcomingViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LessonsUpcomingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private LessonsUpcomingViewModel viewModel;
    private LessonsViewModel lessonsViewModel;
    private LessonsUpcomingRecyclerAdapter recyclerAdapter;
    private List<Lesson> lessonList;

    @BindView(R.id.fragment_lessons_upcoming_rv)
    RecyclerView rvUpcomingLessons;



    public LessonsUpcomingFragment() {
        // Required empty public constructor
    }

    public static LessonsUpcomingFragment newInstance(String param1, String param2) {
        LessonsUpcomingFragment fragment = new LessonsUpcomingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lessons_upcoming, container, false);
        ButterKnife.bind(this, v);
        rvUpcomingLessons.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUpcomingLessons.setHasFixedSize(true);
        recyclerAdapter = new LessonsUpcomingRecyclerAdapter();
        rvUpcomingLessons.setAdapter(recyclerAdapter);

        lessonsViewModel = ViewModelProviders.of(this).get(LessonsViewModel.class);
        viewModel = ViewModelProviders.of(this).get(LessonsUpcomingViewModel.class);
        viewModel.getLessons(FirebaseKeys.CHILD_LESSONS_UPCOMING).observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                lessonList = lessons;
                recyclerAdapter = new LessonsUpcomingRecyclerAdapter(lessonList, getActivity(), new LessonsUpcomingRecyclerAdapter.UpcomingRecyclerAdapterListener() {
                    @Override
                    public void onClickBtnUnbook(View v, int position) {
                        Toast.makeText(getActivity(), "Unbooked", Toast.LENGTH_SHORT).show();
                        lessonsViewModel.deleteUpcoming(lessonList.get(position).getHashId());
                    }
                });
                rvUpcomingLessons.setAdapter(recyclerAdapter);

            }
        });

        return v;
    }

}