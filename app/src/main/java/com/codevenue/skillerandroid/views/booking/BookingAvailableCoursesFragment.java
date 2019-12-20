package com.codevenue.skillerandroid.views.booking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.CoursesRecyclerAdapter;
import com.codevenue.skillerandroid.model.courses.Course;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingAvailableCoursesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rv_available_courses)
    RecyclerView rvCourses;
    CoursesRecyclerAdapter coursesRecyclerAdapter;
    List<Course> coursesList;
    TutorsViewModel tutorsViewModel;
    Tutor tutor;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public BookingAvailableCoursesFragment() {
        // Required empty public constructor
    }

    public static BookingAvailableCoursesFragment newInstance(String param1, String param2) {
        BookingAvailableCoursesFragment fragment = new BookingAvailableCoursesFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_available_courses, container, false);
        ButterKnife.bind(this, v);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        initRecyclerView();

        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        observeCourses();
        return v;
    }


    private void observeCourses(){
        tutorsViewModel.getCourses(tutor.getDatabaseReference()).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable List<Course> courses) {
                coursesList = courses;
                coursesRecyclerAdapter = new CoursesRecyclerAdapter(getActivity(), coursesList,
                        new CoursesRecyclerAdapter.CoursesRecyclerViewListener() {
                    @Override
                    public void tvChooseOnClick(View view, int postition) {
                        Course course = coursesList.get(postition);
                        Lesson lesson = Lesson.getInstance();
                        lesson.setCourse(course);
                        //TODO Change color of card
                    }
                });
                rvCourses.setAdapter(coursesRecyclerAdapter);
            }
        });
    }

    private void initRecyclerView() {
        rvCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCourses.setHasFixedSize(true);
        coursesRecyclerAdapter = new CoursesRecyclerAdapter();
        rvCourses.setAdapter(coursesRecyclerAdapter);
    }

    @OnClick(R.id.fragment_booking_lessons_next)
    public void onClickBtnNext(View view){
        Lesson lesson = Lesson.getInstance();
        if (lesson.getCourse() != null)
            fragmentTransaction.replace(R.id.main_content, new BookingDateFragment()).commit();
        else
            Toast.makeText(getActivity(), "Choose a lesson to continue", Toast.LENGTH_SHORT).show();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            tutor = mListener.getTutor();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        Tutor getTutor();
    }
}
