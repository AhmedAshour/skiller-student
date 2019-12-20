package com.codevenue.skillerandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.ItemClickListener;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewholders.TutorsViewHolder;
import com.codevenue.skillerandroid.views.tutors.TutorProfileActivity;

import java.util.List;

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

