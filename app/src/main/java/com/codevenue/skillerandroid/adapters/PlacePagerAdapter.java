package com.codevenue.skillerandroid.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.codevenue.skillerandroid.views.booking.PrivatePlaceFragment;
import com.codevenue.skillerandroid.views.booking.PublicPlaceFragment;

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
