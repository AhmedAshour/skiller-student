package com.codevenue.skillerandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Experience;
import com.codevenue.skillerandroid.viewholders.ExperienceViewHolder;

import java.util.List;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceViewHolder>{

    private Context context;
    private List<Experience> experienceList;

    public ExperienceRecyclerAdapter(Context context, List<Experience> experiences) {
        this.context = context;
        this.experienceList = experiences;
    }

    public ExperienceRecyclerAdapter() {
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience, parent, false);
        return new ExperienceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = experienceList.get(position);
        if (position == 0)
            holder.startLine.setVisibility(View.GONE);
        if (position == getItemCount() - 1)
            holder.endLine.setVisibility(View.GONE);
        holder.tvExpDesc.setText(experience.getCompany());
        holder.tvExpTitle.setText(experience.getTitle());
        holder.tvExpStartDate.setText(experience.getStartDate().toString());
        holder.tvExpEndDate.setText(experience.getEndDate().toString());
    }

    @Override
    public int getItemCount() {
        if(experienceList == null) return 0;
        return experienceList.size();
    }
}
