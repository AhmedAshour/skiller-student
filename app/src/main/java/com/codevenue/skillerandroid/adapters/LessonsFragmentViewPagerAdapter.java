package com.codevenue.skillerandroid.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codevenue.skillerandroid.views.lessons.LessonsRequestsFragment;
import com.codevenue.skillerandroid.views.lessons.LessonsTakenFragment;
import com.codevenue.skillerandroid.views.lessons.LessonsUpcomingFragment;

public class LessonsFragmentViewPagerAdapter extends FragmentPagerAdapter {

    public LessonsFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new LessonsRequestsFragment();
        else if (position == 1)
            fragment = new LessonsUpcomingFragment();
        else if (position == 2)
            fragment = new LessonsTakenFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0)
            title = "Requests";
        else if (position == 1)
            title = "Upcoming";
        else if (position == 2)
            title = "Finished";
        return title;
    }
}
