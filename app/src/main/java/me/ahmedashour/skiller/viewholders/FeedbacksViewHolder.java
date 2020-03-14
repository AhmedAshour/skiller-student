package me.ahmedashour.skiller.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;

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
