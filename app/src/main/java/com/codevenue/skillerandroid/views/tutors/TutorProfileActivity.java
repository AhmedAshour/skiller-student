package com.codevenue.skillerandroid.views.tutors;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;
import com.codevenue.skillerandroid.views.booking.BookingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TutorProfileActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,
        View.OnClickListener, TutorReviewFragment.OnFragmentInteractionListener, TutorOverviewFragment.OnFragmentInteractionListener {

    @BindView(R.id.tutor_profile_tab_layout)
    TabLayout tutorTabs;
    @BindView(R.id.tv_tutor_hours_profile)
    TextView tvTutorHours;
    @BindView(R.id.tv_tutor_name_profile)
    TextView tvTutorName;
    @BindView(R.id.tv_tutor_rate_profile)
    TextView tvTutorRate;

    @BindView(R.id.tutor_profile_btn_book)
    Button btnBook;
    @BindView(R.id.civ_tutor_profile_photo)
    CircleImageView civTutorProfile;

    private FragmentManager fragmentManager;
    private TutorsViewModel tutorsViewModel;
    private Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        ButterKnife.bind(this);

        tutorTabs.addOnTabSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.tutor_fragment_container, new TutorOverviewFragment()).commit();

        Bundle bundle = getIntent().getExtras();

        tutor = (Tutor) bundle.getSerializable("TUTOR_PROFILE");

        Log.d("TUTOR PROFILE", tutor.getFullName());
        Log.d("TUTOR PROFILE FIRE", tutor.getDatabaseReference());


        tvTutorName.setText(tutor.getFullName());
        tvTutorHours.setText(tutor.getNumExperienceHours());
        Glide.with(this).load(tutor.getImageURL()).into(civTutorProfile);

        bindView(tutor.getDatabaseReference());

        btnBook.setOnClickListener(this);
    }

    private void bindView(String tutorID){
        Log.d("i am in", tutorID);

        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        tutorsViewModel.getTutorInfo(tutorID)
                .observe(this, new Observer<Tutor>() {
                    @Override
                    public void onChanged(@Nullable Tutor theTutor) {
                        tutor = theTutor;
                        Log.d("tutor profile bind", tutor.getFullName());
                        tvTutorName.setText(tutor.getFullName());
                        tvTutorHours.setText(tutor.getNumExperienceHours());
                    }
                });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        fragmentManager = getSupportFragmentManager();
        switch (tab.getPosition()) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.tutor_fragment_container, new TutorOverviewFragment()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.tutor_fragment_container, new TutorReviewFragment()).commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tutor_profile_btn_book:
                Bundle bundle = new Bundle();
                bundle.putSerializable("Tutor", tutor);
                Intent intent = new Intent(this, BookingActivity.class);
                intent.putExtras(bundle);
                this.startActivity(intent);

        }
    }

    @Override
    public Tutor getTutor() {
        return tutor;
    }
}