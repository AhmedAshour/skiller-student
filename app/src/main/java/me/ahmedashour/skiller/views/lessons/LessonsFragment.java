package me.ahmedashour.skiller.views.lessons;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.LessonsFragmentViewPagerAdapter;

public class LessonsFragment extends Fragment {
    @BindView(R.id.fragment_lessons_tl)
    TabLayout tabsLayout;
    @BindView(R.id.fragment_lessons_vp)
    ViewPager viewPager;

    private LessonsFragmentViewPagerAdapter adapter;

    public LessonsFragment() {
    }

    public static LessonsFragment newInstance(String param1, String param2) {
        LessonsFragment fragment = new LessonsFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lessons, container, false);
        ButterKnife.bind(this, v);

        adapter = new LessonsFragmentViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabsLayout.setupWithViewPager(viewPager);

        return v;
    }

}