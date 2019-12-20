package com.codevenue.skillerandroid.adapters.lessons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewholders.lessons.LessonsUpcomingViewHolder;

import java.util.List;

public class LessonsUpcomingRecyclerAdapter extends RecyclerView.Adapter<LessonsUpcomingViewHolder> {

    private UpcomingRecyclerAdapterListener mListener;
    private List<Lesson> lessonList;
    private Context context;

    public LessonsUpcomingRecyclerAdapter() {
    }

    public LessonsUpcomingRecyclerAdapter(List<Lesson> lessonList, Context context,
                                          UpcomingRecyclerAdapterListener mListener) {
        this.lessonList = lessonList;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public LessonsUpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_upcoming, parent, false);
        return new LessonsUpcomingViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsUpcomingViewHolder holder, int position) {
        final Lesson lesson = lessonList.get(position);
        try {
            Log.d("TUTOR_LESSON", lesson.getTutor().getFullName());
            Glide.with(context).load(lesson.getTutor().getImageURL()).into(holder.ivTitleProfilePic);
            Glide.with(context).load(lesson.getTutor().getImageURL()).into(holder.ivProfilePicture);
            holder.tvTitleViewPrice.setText(lesson.getCourse().getPrice());
            holder.tvTitleViewCurrency.setText("L.E");
            holder.tvTitleViewSkill.setText(lesson.getCourse().getCourseTitle());
            holder.tvTitleViewDate.setText(lesson.getDate().toString());
            holder.tvTitleViewLocation.setText(lesson.getLocation().toString());
            holder.tvName.setText(lesson.getTutor().getFullName());
            holder.tvRate.setText(lesson.getTutor().getRating());
            holder.tvSkillName.setText(lesson.getCourse().getCourseTitle());
            holder.tvTimeStart.setText(lesson.getStartTime().toString());
            holder.tvDate.setText(lesson.getDate().toString());
            holder.tvLocation.setText(lesson.getLocation().toString());
            holder.tvPrice.setText(lesson.getCourse().getPrice());
            holder.tvCurrency.setText("L.E");
            holder.tvPaymentMethod.setText("VISA");
            holder.tvNotes.setText(lesson.getCourse().getNotes());
        }catch (NullPointerException e){}

    }

    @Override
    public int getItemCount() {
        if(lessonList == null) return 0;
        return lessonList.size();
    }
    public interface UpcomingRecyclerAdapterListener{
        void onClickBtnUnbook(View v, int position);
    }
}