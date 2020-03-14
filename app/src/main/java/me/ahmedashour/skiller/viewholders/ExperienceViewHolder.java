package me.ahmedashour.skiller.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;

public class ExperienceViewHolder extends RecyclerView.ViewHolder {

    public @BindView(R.id.item_experience_title)
    TextView tvExpTitle;
    public @BindView(R.id.item_experience_description)
    TextView tvExpDesc;
    public @BindView(R.id.item_experience_start_date)
    TextView tvExpStartDate;
    public @BindView(R.id.item_experience_end_date)
    TextView tvExpEndDate;
    public @BindView(R.id.item_experience_start_line)
    View startLine;
    public @BindView(R.id.item_experience_end_line)
    View endLine;
    public ExperienceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
