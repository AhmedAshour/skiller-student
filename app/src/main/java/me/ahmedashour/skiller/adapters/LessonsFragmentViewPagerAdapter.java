package me.ahmedashour.skiller.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import me.ahmedashour.skiller.views.lessons.LessonsRequestsFragment;
import me.ahmedashour.skiller.views.lessons.LessonsTakenFragment;
import me.ahmedashour.skiller.views.lessons.LessonsUpcomingFragment;

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
