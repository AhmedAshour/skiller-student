package com.codevenue.skillerandroid.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.ItemClickListener;
import com.codevenue.skillerandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
//import de.hdodenhof.circleimageview.CircleImageView;

public class TutorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Nullable
    @BindView(R.id.item_tutor_iv_profile_pic)
    ImageView ivTutorPhoto;
    @Nullable
    @BindView(R.id.civ_tutor_photo)
    CircleImageView civTutorPhoto;
    @Nullable
    @BindView(R.id.item_tutor_tv_name)
    TextView tvTutorName;
    @Nullable
    @BindView(R.id.item_tutor_tv_title)
    TextView tvTutorTitle;
    @Nullable
    @BindView(R.id.item_tutor_tv_experience_hours)
    TextView tvTutorHours;
    @Nullable
    @BindView(R.id.item_tutor_tv_location)
    TextView tvTutorLocation;
    @Nullable
    @BindView(R.id.item_tutor_tv_price)
    TextView tvTutorPrice;
    @Nullable
    @BindView(R.id.tv_tutor_rating)
    TextView tvTutorRating;

    private ItemClickListener itemClickListener;

    public TutorsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setTutorRating(String rating) {
        tvTutorRating.setText(rating);
    }

    public void setIvTutorPhoto(String photoUrl) {
        Glide.with(itemView.getContext()).load(photoUrl).into(ivTutorPhoto);
    }
    public void setCivTutorPhoto(String photoUrl) {
        Glide.with(itemView.getContext()).load(photoUrl).into(civTutorPhoto);
    }

    public void setTutorName(String tutorName) {
        tvTutorName.setText(tutorName);
    }

    public void setTutorTitle(String tutorTitle) {
        tvTutorTitle.setText(tutorTitle);
    }


    public void setTutorHours(String tutorHours) {
        tvTutorHours.setText(tutorHours+ " Experience Hours");
    }

    public void setTutorLocation(String tutorLocation) {
        tvTutorLocation.setText(tutorLocation);
    }


    public void setTutorPrice(String tutorPrice) {
        tvTutorPrice.setText(tutorPrice);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClickListener(v, getAdapterPosition(), false);
    }


}