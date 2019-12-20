package com.codevenue.skillerandroid.views.lessons;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.lessons.LessonsRequestsRecyclerAdapter;
import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewmodels.LessonsViewModel;
import com.codevenue.skillerandroid.viewmodels.lessons.LessonsRequestsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsRequestsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.fragment_lessons_requests_rv)
    RecyclerView rvReqs;

    private LessonsRequestsRecyclerAdapter requestsRecyclerAdapter;
    private LessonsRequestsViewModel requestsViewModel;
    private LessonsViewModel lessonsViewModel;
    private List<Lesson> lessonList;


    public LessonsRequestsFragment() {
        // Required empty public constructor
    }

    public static LessonsRequestsFragment newInstance(String param1, String param2) {
        LessonsRequestsFragment fragment = new LessonsRequestsFragment();
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
        View v = inflater.inflate(R.layout.fragment_lessons_requests, container, false);
        ButterKnife.bind(this, v);

        rvReqs.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReqs.setHasFixedSize(true);
        requestsRecyclerAdapter = new LessonsRequestsRecyclerAdapter();
        rvReqs.setAdapter(requestsRecyclerAdapter);

        lessonsViewModel = ViewModelProviders.of(this).get(LessonsViewModel.class);
        requestsViewModel = ViewModelProviders.of(this).get(LessonsRequestsViewModel.class);
        requestsViewModel.getLessons(FirebaseKeys.CHILD_LESSONS_REQUESTS).observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                lessonList = lessons;
                Log.d("LESSONS_IN_FRAG", lessonList.size()+"");
                requestsRecyclerAdapter = new LessonsRequestsRecyclerAdapter(lessonList, getActivity(), new LessonsRequestsRecyclerAdapter.RequestsRecyclerAdapterListener() {
                    @Override
                    public void onClickBtnCancel(View v, int position) {
                        lessonsViewModel.deleteRequest(lessonList.get(position).getHashId());
                    }
                });
                rvReqs.setAdapter(requestsRecyclerAdapter);
            }
        });
        return v;
    }

}