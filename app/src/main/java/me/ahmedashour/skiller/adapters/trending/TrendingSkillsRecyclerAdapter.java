package me.ahmedashour.skiller.adapters.trending;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Tutor;
import me.ahmedashour.skiller.viewholders.TutorsViewHolder;

public class TrendingSkillsRecyclerAdapter extends RecyclerView.Adapter<TutorsViewHolder> {

    List<String> skillsList;
    Context context;
    Tutor tutor;

    public TrendingSkillsRecyclerAdapter(List<String> skillsList, Context context) {
        this.context = context;
        this.skillsList = skillsList;
    }

    @NonNull
    @Override
    public TutorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending_tutor_skills, parent, false);
        return new TutorsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorsViewHolder holder, int position) {
/*
        holder.setTvTrendingTutorSkill(skillsList.get(position));
*/
    }

    @Override
    public int getItemCount() {
        if (skillsList != null) return skillsList.size();
        else return 0;
    }
}
