package me.ahmedashour.skiller.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.ItemClickListener;
import me.ahmedashour.skiller.R;

public class SkillsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;

    public @BindView(R.id.item_skill_cv)
    RelativeLayout cvSkill;

    public @BindView(R.id.item_skill_tv_title)
    TextView tvSkillTitle;
    public @BindView(R.id.item_skill_iv_background)
    ImageView imgSkill;

    public SkillsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClickListener(v, getAdapterPosition(), false);
    }
}
