package me.ahmedashour.skiller.views.tutors;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.viewmodels.TutorsViewModel;
import me.ahmedashour.skiller.views.booking.BookingActivity;

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