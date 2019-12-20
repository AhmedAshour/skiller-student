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
import android.widget.CalendarView;
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Date;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.users.Tutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingDateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.fragment_booking_date_cv) CalendarView cvDate;

    public BookingDateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_date, container, false);
        ButterKnife.bind(this, v);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Lesson lesson = Lesson.getInstance();
                Date date = new Date(String.valueOf(dayOfMonth), String.valueOf(month+1),String.valueOf(year));
                lesson.setDate(date);
            }
        });
        return v;
    }
    @OnClick(R.id.btn_next_fragment_booking_date)
    public void onClickBtnNext(View view){
        Lesson lesson = Lesson.getInstance();
        if (lesson.getDate() != null)
            fragmentTransaction.replace(R.id.main_content, new BookingTimeFragment()).commit();
        else
            Toast.makeText(getActivity(), "Choose a date to continue", Toast.LENGTH_SHORT).show();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        Tutor getTutor();
    }
}
