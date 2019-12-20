package com.codevenue.skillerandroid.views.fragments;

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
import com.codevenue.skillerandroid.adapters.SkillsRecyclerAdapter;
import com.codevenue.skillerandroid.model.misc.Skill;
import com.codevenue.skillerandroid.viewmodels.SkillsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<Skill> skillsList;
    private SkillsViewModel skillsViewModel;

    SkillsRecyclerAdapter skillsRecyclerAdapter;
    @BindView(R.id.rv_skills)
    RecyclerView rvSkills;

    public SkillsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SkillsFragment newInstance(String param1, String param2) {
        SkillsFragment fragment = new SkillsFragment();
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
        View v = inflater.inflate(R.layout.fragment_skills, container, false);
        ButterKnife.bind(this, v);

        rvSkills.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSkills.setHasFixedSize(true);
        skillsRecyclerAdapter = new SkillsRecyclerAdapter();
        rvSkills.setAdapter(skillsRecyclerAdapter);
        skillsViewModel = ViewModelProviders.of(this).get(SkillsViewModel.class);
        skillsViewModel.getSkills().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(@Nullable List<Skill> skills) {
                skillsList = skills;
                skillsRecyclerAdapter = new SkillsRecyclerAdapter(skillsList, getActivity());
                Log.d("SkillFragment", skillsList.get(0).toString());
                Log.d("SKILLS SIZE", skills.size() + "");
                Log.d("ITEMCOUNTBEFORE", skillsRecyclerAdapter.getItemCount() + "");
                Log.d("ITEMCOUNTLIST", skillsList.size() + "");
                rvSkills.setAdapter(skillsRecyclerAdapter);
                Log.d("ITEMCOUNTAFTER", skillsRecyclerAdapter.getItemCount() + "");
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
