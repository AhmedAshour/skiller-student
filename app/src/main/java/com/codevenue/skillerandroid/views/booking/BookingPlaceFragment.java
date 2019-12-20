package com.codevenue.skillerandroid.views.booking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.PlacePagerAdapter;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.users.Tutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BookingPlaceFragment extends Fragment {


    @BindView(R.id.tl_booking_fragment_place)
    TabLayout tabLayout;
    @BindView(R.id.vp_fragment_place)
    ViewPager viewPager;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public BookingPlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_place, container, false);
        ButterKnife.bind(this, v);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        tabLayout.addTab(tabLayout.newTab().setText("Public"));
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PlacePagerAdapter placePagerAdapter = new PlacePagerAdapter(fragmentManager, tabLayout.getTabCount());
        viewPager.setAdapter(placePagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return v;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        Tutor getTutor();
    }
}
