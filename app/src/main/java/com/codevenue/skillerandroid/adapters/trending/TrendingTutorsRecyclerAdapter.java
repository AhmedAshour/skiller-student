package com.codevenue.skillerandroid.adapters.trending;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewholders.TutorsViewHolder;

import java.util.List;

public class TrendingTutorsRecyclerAdapter extends RecyclerView.Adapter<TutorsViewHolder> {

    List<Tutor> tutorsList;
    Context context;
    Tutor tutor;

    public TrendingTutorsRecyclerAdapter(){}

    public TrendingTutorsRecyclerAdapter(List<Tutor>tutorsList, Context context){
        this.tutorsList = tutorsList;
        this.context = context;

    }

    @NonNull
    @Override
    public TutorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending_tutor,parent,false);
        return new TutorsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorsViewHolder holder, int position) {
        tutor = tutorsList.get(position);
        holder.setCivTutorPhoto(tutor.getImageURL());
        holder.setTutorName(tutor.getFullName());
        holder.setTutorTitle(tutor.getTitle());
        holder.setTutorPrice(tutor.getPricePerHour());
        holder.setTutorRating(tutor.getRating());
       /* Log.d("TrendingTutorsRecycler", tutor.getSkillsTagsList()+"");
        holder.setRvTrendingTutorsSkills(new TrendingSkillsRecyclerAdapter(tutor.getSkillsTagsList(), context));*/

    }

    @Override
    public int getItemCount() {
        if (tutorsList != null) return tutorsList.size();
        else return 0;
    }
}
