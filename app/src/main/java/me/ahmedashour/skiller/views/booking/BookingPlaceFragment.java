package me.ahmedashour.skiller.views.booking;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.PlacePagerAdapter;
import me.ahmedashour.skiller.model.users.Tutor;


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
