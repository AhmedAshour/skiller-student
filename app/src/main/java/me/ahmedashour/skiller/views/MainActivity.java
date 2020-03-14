package me.ahmedashour.skiller.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.util.BottomNavigationViewHelper;
import me.ahmedashour.skiller.views.fragments.AcademyFragment;
import me.ahmedashour.skiller.views.fragments.SkillsFragment;
import me.ahmedashour.skiller.views.lessons.LessonsFragment;

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
