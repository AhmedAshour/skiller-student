package me.ahmedashour.skiller.views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Student;
import me.ahmedashour.skiller.viewmodels.StudentViewModel;
import me.ahmedashour.skiller.views.more.ProfileEditActivity;

public class MoreFragment extends Fragment {

    private String TAG = MoreFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private StudentViewModel studentViewModel;

    //Views
    @BindView(R.id.fragment_more_iv_profile_picture)
    CircleImageView ivProfilePicture;
    @BindView(R.id.fragment_more_tv_profile_name)
    TextView tvName;
    @BindView(R.id.fragment_more_tv_profile_city)
    TextView tvCity;


    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        View v = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, v);

        bindViewModel();
        return v;
    }



    private void bindViewModel() {
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getStudentData().observe(getViewLifecycleOwner(), new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                Log.d(TAG, student + "");
                bindViewsWithData(student);
            }
        });
    }

    private void bindViewsWithData(Student student) {
        Log.d(TAG, "bindViewsWithData: " + student);
        Glide.with(this).load(student.getImageURL()).into(ivProfilePicture);
        tvName.setText(student.getFullName());
        tvCity.setText(student.getContact().getLocation().getCity());

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
       /* if (context instanceof OnFragmentInteractionListener) {
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @OnClick(R.id.fragment_more_tv_sign_out)
    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(getActivity())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                    }
                });
    }
    @OnClick(R.id.fragment_more_btn_profile_edit)
    public void openEditProfile(View view) {
        Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
        startActivity(intent);
    }
}
