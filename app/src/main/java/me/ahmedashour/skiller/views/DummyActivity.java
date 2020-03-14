package me.ahmedashour.skiller.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Tutor;

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
