package me.ahmedashour.skiller.views.booking;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.util.NoSwipeViewPager;
import me.ahmedashour.skiller.viewmodels.LessonsViewModel;
import me.ahmedashour.skiller.viewmodels.StudentViewModel;
import me.ahmedashour.skiller.viewmodels.TutorsViewModel;

public class BookingActivity extends AppCompatActivity implements
        BookingDateFragment.OnFragmentInteractionListener, BookingPlaceFragment.OnFragmentInteractionListener,
        BookingConfirmationFragment.OnFragmentInteractionListener, BookingTimeFragment.OnFragmentInteractionListener,
        BookingAvailableCoursesFragment.OnFragmentInteractionListener,
        PublicPlaceFragment.OnFragmentInteractionListener,PrivatePlaceFragment.OnFragmentInteractionListener{

    @BindView(R.id.activity_booking_view_pager)
    NoSwipeViewPager mViewPager;

    Tutor tutor;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FragmentManager fragmentManager;
    private TutorsViewModel tutorsViewModel;
    private StudentViewModel studentViewModel;
    private LessonsViewModel lessonsViewModel;



    public BookingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        tutor = (Tutor) bundle.getSerializable("Tutor");

        Log.d("TUTOR BOOKING", tutor.getFullName());
        Log.d("TUTOR BOOKING id", tutor.getDatabaseReference());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_booking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public Tutor getTutor() {
        return tutor;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BookingAvailableCoursesFragment();
                case 1:
                    return new BookingDateFragment();
                case 2:
                    return new BookingTimeFragment();
                case 3:
                    return new BookingPlaceFragment();
                case 4:
                    return new BookingConfirmationFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
