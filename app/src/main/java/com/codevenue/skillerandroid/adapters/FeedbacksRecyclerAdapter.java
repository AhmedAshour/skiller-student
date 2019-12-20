package com.codevenue.skillerandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Feedback;
import com.codevenue.skillerandroid.viewholders.FeedbacksViewHolder;

import java.util.List;

public class FeedbacksRecyclerAdapter extends RecyclerView.Adapter<FeedbacksViewHolder> {

    private Context context;
    private List<Feedback> feedbacks;


    public FeedbacksRecyclerAdapter(Context context, List<Feedback> genericItemList) {
        this.context = context;
        this.feedbacks = genericItemList;
    }

    public FeedbacksRecyclerAdapter() {
    }

    @NonNull
    @Override
    public FeedbacksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tutor_profile_review, parent, false);
        return new FeedbacksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbacksViewHolder holder, int position) {
            Feedback feedback = feedbacks.get(position);
            holder.tvReviewDate.setText(feedback.getDate().toString());
            holder.tvReviewInnerText.setText(feedback.getReview());
    }

    @Override
    public int getItemCount() {
        if(feedbacks == null) return 0;
        return feedbacks.size();
    }
}
