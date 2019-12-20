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

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.FeedbacksRecyclerAdapter;
import com.codevenue.skillerandroid.model.misc.Feedback;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorReviewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rv_reviews)
    RecyclerView rvReviews;
    private List<Feedback> feedbackList;
    private Tutor tutor;
    private FeedbacksRecyclerAdapter feedbacksRecyclerAdapter;
    private TutorsViewModel tutorsViewModel;

    public TutorReviewFragment() {
        // Required empty public constructor
    }

    public static TutorReviewFragment newInstance(String param1, String param2) {
        TutorReviewFragment fragment = new TutorReviewFragment();
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
        View view = inflater.inflate(R.layout.fragment_tutor_review, container, false);
        ButterKnife.bind(this, view);
        //feedbackList.addAll(tutor.getFeedBacksList());
        rvReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReviews.setHasFixedSize(true);
        feedbacksRecyclerAdapter = new FeedbacksRecyclerAdapter();
        rvReviews.setAdapter(feedbacksRecyclerAdapter);
        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        Log.d("ID", tutor.getDatabaseReference());
        tutorsViewModel.getFeedbacks(tutor.getDatabaseReference().trim()).observe(this, new Observer<List<Feedback>>() {
            @Override
            public void onChanged(@Nullable List<Feedback> feedbacks) {
                feedbackList = feedbacks;
                feedbacksRecyclerAdapter = new FeedbacksRecyclerAdapter(getActivity(), feedbackList);
                rvReviews.setAdapter(feedbacksRecyclerAdapter);
                Log.d("REVIEWS SIZE", feedbackList.size()+"");
                Log.d("GENERIC SIZE", feedbacks.size()+"");

            }
        });
        return view;
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

        //feedbackList = new ArrayList<>();

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