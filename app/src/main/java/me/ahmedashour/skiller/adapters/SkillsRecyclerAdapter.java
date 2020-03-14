package me.ahmedashour.skiller.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import me.ahmedashour.skiller.ItemClickListener;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.misc.Skill;
import me.ahmedashour.skiller.viewholders.SkillsViewHolder;
import me.ahmedashour.skiller.views.tutors.TutorsActivity;

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
