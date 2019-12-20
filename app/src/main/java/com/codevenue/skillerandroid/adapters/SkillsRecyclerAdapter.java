package com.codevenue.skillerandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.ItemClickListener;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Skill;
import com.codevenue.skillerandroid.viewholders.SkillsViewHolder;
import com.codevenue.skillerandroid.views.tutors.TutorsActivity;

import java.util.List;

public class SkillsRecyclerAdapter extends RecyclerView.Adapter<SkillsViewHolder> {
    private List<Skill> skillsList;
    private Context context;

    public SkillsRecyclerAdapter(List<Skill> skillsList, Context context) {
        this.skillsList = skillsList;
        this.context = context;
    }

    public SkillsRecyclerAdapter() {
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill, parent, false);
        return new SkillsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        holder.tvSkillTitle.setText(skillsList.get(position).getSkillTitle());
        holder.tvSkillTitle.bringToFront();
        Glide.with(context).load(skillsList.get(position).getBackgroundImage()).into(holder.imgSkill);

        holder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, boolean isLongClick) {
                Bundle bundle = new Bundle();
                bundle.putString("SKILL_CLICKED_TITLE", skillsList.get(position).getSkillTitle());
                Intent intent = new Intent(context, TutorsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (skillsList == null) return 0;
        return skillsList.size();
    }
}
