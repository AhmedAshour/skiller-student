package me.ahmedashour.skiller.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.ahmedashour.skiller.databinding.ItemSkillBinding
import me.ahmedashour.skiller.model.data.Skill
import timber.log.Timber

class SkillsAdapter : ListAdapter<Skill, SkillsAdapter.ViewHolder>(SkillDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.i("onCreateViewHolder Called!")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.i("onBindViewHolder Called!")
        val currentItem = getItem(position)
        holder.bindView(currentItem)
    }


    class ViewHolder private constructor(val binding: ItemSkillBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bindView(currentItem: Skill) {
            binding.itemSkillTvTitle.text = currentItem.title
            Glide.with(binding.root).load(currentItem.backgroundImage).into(binding.itemSkillIvBackground)
        }
    }


}

class SkillDiffUtilCallback : DiffUtil.ItemCallback<Skill>() {
    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        Timber.d("is ${oldItem.title} the same as ${newItem.title} ${oldItem.title == newItem.title}")
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        Timber.d("is ${oldItem.title} content same as ${newItem.title} ${oldItem.title == newItem.title}")
        return oldItem == newItem
    }

}