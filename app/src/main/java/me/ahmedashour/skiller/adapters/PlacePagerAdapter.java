package me.ahmedashour.skiller.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import me.ahmedashour.skiller.views.booking.PrivatePlaceFragment;
import me.ahmedashour.skiller.views.booking.PublicPlaceFragment;

public class PlacePagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;

    public PlacePagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PublicPlaceFragment();
            case 1:
                return new PrivatePlaceFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
