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

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.lessons.LessonsFinishedRecyclerAdapter;
import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewmodels.LessonsViewModel;
import com.codevenue.skillerandroid.viewmodels.lessons.LessonsFinishedViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LessonsTakenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private LessonsFinishedViewModel lessonsViewModel;
    private LessonsFinishedRecyclerAdapter recyclerAdapter;
    private List<Lesson> lessonList;

    @BindView(R.id.fragment_lessons_taken_rv)
    RecyclerView rvFinished;


    public LessonsTakenFragment() {
        // Required empty public constructor
    }

    public static LessonsTakenFragment newInstance(String param1, String param2) {
        LessonsTakenFragment fragment = new LessonsTakenFragment();
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
        View v = inflater.inflate(R.layout.fragment_lessons_taken, container, false);
        ButterKnife.bind(this, v);

        rvFinished.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFinished.setHasFixedSize(true);
        recyclerAdapter = new LessonsFinishedRecyclerAdapter();
        rvFinished.setAdapter(recyclerAdapter);

        lessonsViewModel = ViewModelProviders.of(this).get(LessonsFinishedViewModel.class);
        lessonsViewModel.getLessons(FirebaseKeys.CHILD_LESSONS_FINISHED).observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                lessonList = lessons;
                recyclerAdapter = new LessonsFinishedRecyclerAdapter(lessonList, getActivity());
                rvFinished.setAdapter(recyclerAdapter);
            }
        });


        return v;
    }

}