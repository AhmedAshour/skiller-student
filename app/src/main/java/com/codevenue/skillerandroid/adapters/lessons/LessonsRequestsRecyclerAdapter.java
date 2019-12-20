package com.codevenue.skillerandroid.adapters.lessons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewholders.lessons.LessonsRequestsViewHolder;
import com.codevenue.skillerandroid.views.tutors.TutorProfileActivity;

import java.util.List;

public class LessonsRequestsRecyclerAdapter extends RecyclerView.Adapter<LessonsRequestsViewHolder> {


    private RequestsRecyclerAdapterListener mListener;
    private List<Lesson> lessonList;
    private Context context;


    public LessonsRequestsRecyclerAdapter(List<Lesson> lessonList,
                                          Context context,RequestsRecyclerAdapterListener mListener) {
        this.lessonList = lessonList;
        this.context = context;
        this.mListener = mListener;
    }

    public LessonsRequestsRecyclerAdapter() {
    }

    @NonNull
    @Override
    public LessonsRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson_request, parent, false);
        return new LessonsRequestsViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonsRequestsViewHolder holder, int position) {
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
            holder.tvTitleViewRequestStatus.setText("Pending");
            holder.tvTitleStudentName.setText(lesson.getTutor().getFullName());
            holder.tvName.setText(lesson.getTutor().getFullName());
            holder.tvRate.setText(lesson.getTutor().getRating());
            holder.tvSkillName.setText(lesson.getCourse().getCourseTitle());
            holder.tvTimeStart.setText(lesson.getStartTime().toString());
            holder.tvTimeEnd.setText(lesson.getEndTime().toString());
            holder.tvDate.setText(lesson.getDate().toString());
            holder.tvLocation.setText(lesson.getLocation().toString()+"\n"+lesson.getLocation().getAddress());
            holder.tvPrice.setText(lesson.getCourse().getPrice());
            holder.tvCurrency.setText("L.E");
            holder.tvNotes.setText(lesson.getCourse().getNotes());

            holder.btnEnterProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("TUTOR_PROFILE", lesson.getTutor());
                    Intent intent = new Intent(context, TutorProfileActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        if (lessonList == null) return 0;
        return lessonList.size();
    }
    public interface RequestsRecyclerAdapterListener{
         void onClickBtnCancel(View v, int position);
    }
}