package com.codevenue.skillerandroid.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codevenue.skillerandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbacksViewHolder extends RecyclerView.ViewHolder {

    public @BindView(R.id.card_tutor_review_profile_pic)
    ImageView ivReview;
    public @BindView(R.id.card_tutor_review_name)
    TextView tvReviewName;
    public @BindView(R.id.card_tutor_review_date)
    TextView tvReviewDate;
    public @BindView(R.id.card_tutor_review_inner_text)
    TextView tvReviewInnerText;

    public FeedbacksViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
