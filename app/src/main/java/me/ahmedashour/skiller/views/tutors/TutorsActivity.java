package me.ahmedashour.skiller.views.tutors;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.TutorsRecyclerAdapter;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.viewmodels.TutorsViewModel;

public class TutorsActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutors_ib_back)
    ImageButton ibBack;
    @BindView(R.id.rv_tutors_list)
    RecyclerView rvTutorsList;
    private List<Tutor> tutorsList;
    private TutorsViewModel tutorsViewModel;
    private TutorsRecyclerAdapter tutorsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        initRvTutors();
        if(bundle != null) {
            bindViewFilter(bundle.getString("SKILL_CLICKED_TITLE"));
        }else {
            bindViewModel();
        }
    }

    @OnClick(R.id.activity_tutors_ib_back)
    public void onClickIbBack(View view){
        finish();
    }

    private void bindViewFilter(String filterString){
        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        tutorsViewModel.getTutorsFilter(filterString.trim())
                .observe(this, new Observer<List<Tutor>>() {
            @Override
            public void onChanged(@Nullable List<Tutor> tutors) {
                onChanges(tutors);
            }
        });

    }

    private void bindViewModel() {
        tutorsViewModel = ViewModelProviders.of(this).get(TutorsViewModel.class);
        tutorsViewModel.getTutors().observe(this, new Observer<List<Tutor>>() {
            @Override
            public void onChanged(@Nullable List<Tutor> tutors) {
                onChanges(tutors);
            }
        });
    }

    private void onChanges(List<Tutor> tutors){
        tutorsList = tutors;
        tutorsRecyclerAdapter = new TutorsRecyclerAdapter(tutorsList, TutorsActivity.this);
        rvTutorsList.setAdapter(tutorsRecyclerAdapter);
    }

    private void initRvTutors() {
        tutorsRecyclerAdapter = new TutorsRecyclerAdapter();
        rvTutorsList.setLayoutManager(new LinearLayoutManager(this));
        rvTutorsList.setHasFixedSize(true);
        rvTutorsList.setAdapter(tutorsRecyclerAdapter);
    }
}