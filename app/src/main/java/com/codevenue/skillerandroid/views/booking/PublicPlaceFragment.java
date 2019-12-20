package com.codevenue.skillerandroid.views.booking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.misc.Location;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicPlaceFragment extends Fragment {
    @BindView(R.id.fragment_booking_place_address_et)
    EditText etAddress;
    @BindView(R.id.fragment_booking_place_district_et)
    EditText etDistrict;
    @BindView(R.id.fragment_booking_place_name_et)
    EditText etPlaceName;
    @BindView(R.id.fragment_booking_place_more_et)
    EditText etMoreInfo;


    String strAdd;
    String strDis;
    String strPlaceName;
    String strMoreInfo;


    private Lesson lesson;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public PublicPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_public_place, container, false);
        ButterKnife.bind(this, v);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        lesson = Lesson.getInstance();

    }

    private void getDataFromFields() {

        strAdd = etAddress.getText().toString();
        strDis = etDistrict.getText().toString();
        strPlaceName = etPlaceName.getText().toString();
        strMoreInfo = etMoreInfo.getText().toString();
        putInLesson(strAdd, strDis, strPlaceName, strMoreInfo);
    }

    private void putInLesson(String strAdd, String strDis, String strPlaceName, String strMoreInfo) {
        Location location = new Location(strAdd, strDis, strPlaceName, strMoreInfo);
        lesson.setLocation(location);
    }


    @OnClick(R.id.btn_next_fragment_booking_place)
    public void onClickBtnNext(View view) {
        getDataFromFields();
        if (strPlaceName.isEmpty() || strAdd.isEmpty() || strDis.isEmpty())
            Toast.makeText(getActivity(), "Enter a place to continue", Toast.LENGTH_SHORT).show();
        else
            fragmentTransaction.replace(R.id.main_content, new BookingConfirmationFragment()).commit();


    }


    public interface OnFragmentInteractionListener {
    }
}
