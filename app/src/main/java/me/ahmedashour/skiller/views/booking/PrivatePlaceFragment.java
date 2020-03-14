package me.ahmedashour.skiller.views.booking;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.misc.Lesson;
import me.ahmedashour.skiller.model.misc.Location;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivatePlaceFragment extends Fragment {

    @BindView(R.id.fragment_booking_place_address_et)
    EditText etAddress;
    @BindView(R.id.fragment_booking_place_apart_et)
    EditText etApartment;
    @BindView(R.id.fragment_booking_place_district_et)
    EditText etDistrict;
    @BindView(R.id.fragment_booking_place_floor_et)
    EditText etFloor;
    @BindView(R.id.fragment_booking_place_more_et)
    EditText etMoreInfo;

    String strAdd;
    String strDis;
    String strFloor;
    String strApart;
    String strMoreInfo;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public PrivatePlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_private_place, container, false);
        ButterKnife.bind(this, v);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        return v;
    }


    public void getDataFromFields() {

        strAdd = etAddress.getText().toString();
        strDis = etDistrict.getText().toString();
        strFloor = etFloor.getText().toString();
        strApart = etApartment.getText().toString();
        strMoreInfo = etMoreInfo.getText().toString();
        putInLesson(strAdd, strDis, strApart, strFloor, strMoreInfo);
    }

    @OnClick(R.id.btn_next_fragment_booking_place)
    public void onClickBtnNext(View view) {
        getDataFromFields();
        if (strAdd.isEmpty() || strApart.isEmpty() || strDis.isEmpty() || strFloor.isEmpty())
            Toast.makeText(getActivity(), "Enter a place to continue", Toast.LENGTH_SHORT).show();
        else
            fragmentTransaction.replace(R.id.main_content, new BookingConfirmationFragment()).commit();


    }


    private void putInLesson(String strAdd, String strDis, String strApart, String strFloor, String strMoreInfo) {
        Location location = new Location(strAdd, strApart, strDis, strFloor, strMoreInfo);
        Lesson lesson = Lesson.getInstance();
        lesson.setLocation(location);
    }

    public interface OnFragmentInteractionListener {
    }
}
