package me.ahmedashour.skiller.views.lessons;

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
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.lessons.LessonsUpcomingRecyclerAdapter;
import me.ahmedashour.skiller.constants.FirebaseKeys;
import me.ahmedashour.skiller.model.misc.Lesson;
import me.ahmedashour.skiller.viewmodels.LessonsViewModel;
import me.ahmedashour.skiller.viewmodels.lessons.LessonsUpcomingViewModel;


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