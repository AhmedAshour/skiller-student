package com.codevenue.skillerandroid.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.users.Tutor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyActivity extends AppCompatActivity {

    @BindView(R.id.tv_dummy)
    TextView tvDummy;
    private Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        ButterKnife.bind(this);

        tutor = (Tutor) getIntent().getSerializableExtra("TUTOR_PROFILE");
        tvDummy.setText(tutor.toString());
    }
}
