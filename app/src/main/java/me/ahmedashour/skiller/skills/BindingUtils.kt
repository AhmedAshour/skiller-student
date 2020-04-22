package me.ahmedashour.skiller.skills

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.ahmedashour.skiller.model.data.Skill


@BindingAdapter("skillTitle")
fun TextView.setSkillTitle(item: Skill) {
    text = item.title
}

@BindingAdapter("skillBackground")
fun ImageView.setSkillBackground(item: Skill) {
    Glide.with(context).load(item.backgroundImage).into(this)
}