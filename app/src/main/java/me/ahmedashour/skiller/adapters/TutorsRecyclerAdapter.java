package me.ahmedashour.skiller.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import me.ahmedashour.skiller.ItemClickListener;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.viewholders.TutorsViewHolder;
import me.ahmedashour.skiller.views.tutors.TutorProfileActivity;

public class TutorsRecyclerAdapter extends RecyclerView.Adapter<TutorsViewHolder> {
    private List<Tutor> tutorsList;
    private Context context;
    private Tutor tutor;

    public TutorsRecyclerAdapter(List<Tutor> tutorsList, Context context) {
        this.tutorsList = tutorsList;
        this.context = context;
    }

    public TutorsRecyclerAdapter() {
    }

    @NonNull
    @Override
    public TutorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor, parent, false);
        return new TutorsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorsViewHolder holder, int position) {
        tutor = tutorsList.get(position);
        try {
            holder.setIvTutorPhoto(tutor.getImageURL());
            holder.setTutorName(tutor.getFullName());
            holder.setTutorTitle(tutor.getTitle());
            holder.setTutorHours(tutor.getNumExperienceHours());
            holder.setTutorLocation(tutor.getContact().getLocation().getCity());
            holder.setTutorPrice(tutor.getPricePerHour());
        }catch (NullPointerException e){}
        Log.d("TUTORS ADAPTER BEFORE", tutor.getFullName());


        holder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, boolean isLongClick) {
                tutor = tutorsList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("TUTOR_PROFILE", tutor);
                Log.d("TUTORS ADAPTER", tutor.getDatabaseReference());
                Intent intent = new Intent(context, TutorProfileActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tutorsList != null) return tutorsList.size();
        else return 0;
    }
}

