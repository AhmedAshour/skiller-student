package com.codevenue.skillerandroid.adapters.lessons;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.MyCallback;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.viewholders.lessons.LessonsFinishedViewHolder;
import com.codevenue.skillerandroid.viewmodels.TutorsViewModel;
import com.codevenue.skillerandroid.viewmodels.lessons.LessonsFinishedViewModel;

import java.util.List;

public class LessonsFinishedRecyclerAdapter extends RecyclerView.Adapter<LessonsFinishedViewHolder> {


    private List<Lesson> lessonList;
    private Context context;
    private float fRate;
    private boolean isTrue;


    public LessonsFinishedRecyclerAdapter(List<Lesson> lessonList, Context context) {
        this.lessonList = lessonList;
        this.context = context;
    }

    public LessonsFinishedRecyclerAdapter() {
    }

    @NonNull
    @Override
    public LessonsFinishedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson_finished, parent, false);
        return new LessonsFinishedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsFinishedViewHolder holder, int position) {
        final Lesson lesson = lessonList.get(position);
        try {
            Log.d("TUTOR_LESSON", lesson.getTutor().getFullName());
            Glide.with(context).load(lesson.getTutor().getImageURL()).into(holder.ivProfilePicture);
            holder.tvTitleViewPrice.setText(lesson.getCourse().getPrice());
            holder.tvTitleViewCurrency.setText("L.E");
            holder.tvName.setText(lesson.getTutor().getFullName());
            holder.tvRate.setText(lesson.getTutor().getRating());
            holder.tvSkillName.setText(lesson.getCourse().getCourseTitle());
            holder.tvTimeStart.setText(lesson.getStartTime().toString());
            holder.tvTimeEnd.setText(lesson.getEndTime().toString());
            holder.tvDate.setText(lesson.getDate().toString());
            holder.tvLocation.setText(lesson.getLocation().toString());
            holder.tvPrice.setText(lesson.getCourse().getPrice());
            holder.tvCurrency.setText("L.E");
            holder.tvSkillName.setText(lesson.getCourse().getCourseTitle());
            holder.tvPaymentMethod.setText("VISA");
            holder.tvNotes.setText(lesson.getCourse().getNotes());
        }catch (NullPointerException e){}

        holder.btnRateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKED", "clicked");
                fRate = setUpADialog(lesson);
            }
        });
    }

    private float setUpADialog(final Lesson lesson) {

        final LessonsFinishedViewModel vm = new LessonsFinishedViewModel();

        vm.getIsDoneStudent(lesson.getHashId(), new MyCallback() {
            @Override
            public void onCallback(Boolean bool) {
                setupisTrue(bool);
            }
        });

        Log.d("isTrue", isTrue + "");

        if (isTrue) {
            Log.d("TRUE_OR_FALSE", "INSIDE HERE");
            Toast.makeText(context, "YOU ALREADY REVIEWED THIS TUTOR", Toast.LENGTH_SHORT).show();
            return 0.0f;
        }

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Review");

        //1st view
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final RatingBar ratingBar = new RatingBar(context);
        linearLayout.addView(ratingBar);
        final EditText review = new EditText(context);
        linearLayout.addView(review);
        alertDialog.setView(linearLayout);
        alertDialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isTrue){
                    Toast.makeText(context, "YOU AlREADY REVIEWED THIS LESSON",Toast.LENGTH_SHORT).show();
                    return;
                }
                String strReview = review.getText().toString();
                TutorsViewModel tutorsViewModel = new TutorsViewModel();
                tutorsViewModel.setTutorRating(lesson.getTutorUid(), ratingBar.getRating());
                tutorsViewModel.addToFeedbacksList(lesson.getTutorUid(), ratingBar.getRating(), strReview);
                vm.setIsDoneStudent(lesson.getHashId(), true);
                dialog.cancel();
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
        return ratingBar.getRating();

    }

    @Override
    public int getItemCount() {
        if (lessonList == null) return 0;
        return lessonList.size();
    }

    private void setupisTrue(Boolean isTrue) {
        this.isTrue  = isTrue;
    }
}