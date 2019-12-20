package com.codevenue.skillerandroid.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codevenue.skillerandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
