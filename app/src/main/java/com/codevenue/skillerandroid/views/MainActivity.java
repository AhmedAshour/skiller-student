package com.codevenue.skillerandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.util.BottomNavigationViewHelper;
import com.codevenue.skillerandroid.views.fragments.AcademyFragment;
import com.codevenue.skillerandroid.views.fragments.SkillsFragment;
import com.codevenue.skillerandroid.views.fragments.TrendingFragment;
import com.codevenue.skillerandroid.views.lessons.LessonsFragment;
import com.codevenue.skillerandroid.views.more.ProfileEditActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == navigation.getSelectedItemId()) {
                return true;
            } else {
                switch (item.getItemId()) {
                /*case R.id.navigation_trending:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TrendingFragment()).commit();
                    return true;*/
                    case R.id.navigation_skills:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new SkillsFragment()).commit();
                        return true;
                    case R.id.navigation_lessons:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new LessonsFragment()).commit();
                        return true;
                    case R.id.navigation_academy:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AcademyFragment()).commit();
                        return true;
                    case R.id.navigation_more:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MoreFragment()).commit();
                        return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.fragment_container, new SkillsFragment()).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }


}
