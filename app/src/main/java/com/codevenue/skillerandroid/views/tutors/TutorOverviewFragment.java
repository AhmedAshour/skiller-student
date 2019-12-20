package com.codevenue.skillerandroid.views.tutors;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.ExperienceRecyclerAdapter;
import com.codevenue.skillerandroid.model.misc.Experience;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorOverviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.card_tutor_experience_rv)
    RecyclerView rvExperience;
    @BindView(R.id.card_tutor_overview_inner_text)
    TextView tvBio;
    @BindView(R.id.card_tutor_overview_inner_text_edu)
    TextView tvEdu;

    private List<Experience> experienceList;
    private Tutor tutor;
    private ExperienceRecyclerAdapter experienceRecyclerAdapter;
    private TutorsViewModel tutorsViewModel;


    private OnFragmentInteractionListener mListener;

    public TutorOverviewFragment() {
        // Required empty public constructor
    }

    public static TutorOverviewFragment newInstance(String param1, String param2) {
        TutorOverviewFragment fragment = new TutorOverviewFragment();
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
        View view = inflater.inflate(R.layout.fragment_tutor_overview, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        observeExperience();
        observeBioAndEducation();
        return view;
    }

    private void initRecyclerView() {
        rvExperience.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvExperience.setHasFixedSize(true);
        experienceRecyclerAdapter = new ExperienceRecyclerAdapter();
        rvExperience.setAdapter(experienceRecyclerAdapter);
    }

    private void observeExperience() {
        tutorsViewModel.getExperience(tutor.getDatabaseReference()).observe(this, new Observer<List<Experience>>() {
            @Override
            public void onChanged(@Nullable List<Experience> experiences) {
                experienceList = experiences;
                experienceRecyclerAdapter = new ExperienceRecyclerAdapter(getActivity(), experienceList);
                rvExperience.setAdapter(experienceRecyclerAdapter);
            }
        });
    }

    private void observeBioAndEducation() {
        tutorsViewModel.getTutorInfo(tutor.getDatabaseReference()).observe(this, new Observer<Tutor>() {
            @Override
            public void onChanged(@Nullable Tutor theTutor) {
                tutor = theTutor;
                tvBio.setText(tutor.getBio());
                tvEdu.setText(tutor.getEducation().toString());
            }
        });
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
        Tutor getTutor();
    }

}