package com.codevenue.skillerandroid.views.booking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.misc.Time;
import com.codevenue.skillerandroid.model.users.Tutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingTimeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.fragment_booking_time_time_picker)
    TimePicker tpTime;

    public BookingTimeFragment() {
        // Required empty public constructor
    }

    public static BookingTimeFragment newInstance(String param1, String param2) {
        BookingTimeFragment fragment = new BookingTimeFragment();
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
        View v = inflater.inflate(R.layout.fragment_booking_time, container, false);
        ButterKnife.bind(this, v);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Lesson lesson = Lesson.getInstance();
                String numHrsPerSession = lesson.getCourse().getNumHoursPerSession();

                Time startTime = new Time(String.valueOf(hourOfDay), String.valueOf(minute));
                Time endTime = new Time(String.valueOf(hourOfDay + Integer.valueOf(numHrsPerSession)), String.valueOf(minute));
                lesson.setStartTime(startTime);
                lesson.setEndTime(endTime);


            }
        });
        return v;
    }

    @OnClick(R.id.btn_next_fragment_booking_time)
    public void onClickBtnNext(View view) {
        Lesson lesson = Lesson.getInstance();
        if (lesson.getStartTime() != null)
            fragmentTransaction.replace(R.id.main_content, new BookingPlaceFragment()).commit();
        else
            Toast.makeText(getActivity(), "Choose time to continue", Toast.LENGTH_SHORT).show();

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
