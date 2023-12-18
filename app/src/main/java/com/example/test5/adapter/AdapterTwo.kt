package com.example.test5.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test5.R
import com.example.test5.databinding.ItemLayoutTwoBinding
import com.example.test5.dataclass.ActiveCourse

class AdapterTwo : ListAdapter<ActiveCourse, RecyclerView.ViewHolder>(DataDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTwoBinding.inflate(inflater, parent, false)
        return ActiveCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder) {
            is ActiveCourseViewHolder -> holder.bind(currentItem)
        }
    }

    inner class ActiveCourseViewHolder(private val binding: ItemLayoutTwoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ActiveCourse) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.image)
                    .into(image)

                title.text = data.title
                bookingTime.text = data.bookingTime

                val mainColor = Color.parseColor("#${data.mainColor}")
                val opacity = data.backgroundColorPresent.toInt() * 255 / 100

                val shapeDrawable = ContextCompat.getDrawable(
                    root.context,
                    R.drawable.item_one_rounded
                ) as GradientDrawable

                shapeDrawable.setColor(mainColor)
                shapeDrawable.alpha = opacity

                root.background = shapeDrawable

                bookingTime.setTextColor(mainColor)
                title.setTextColor(mainColor)
            }
        }

    }

    object DataDiffCallBack : DiffUtil.ItemCallback<ActiveCourse>() {
        override fun areItemsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
            return oldItem == newItem
        }
    }
}


