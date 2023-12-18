package com.example.test5.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test5.R
import com.example.test5.databinding.ItemLayoutOneBinding
import com.example.test5.dataclass.NewCourse


class Adapter : ListAdapter<NewCourse, Adapter.NewCourseViewHolder>(DataDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutOneBinding.inflate(inflater, parent, false)
        return NewCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewCourseViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class NewCourseViewHolder(private val binding: ItemLayoutOneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: NewCourse) {
            with(binding) {
                val mainColor = Color.parseColor("#${data.mainColor}")

                val shapeDrawable = ContextCompat.getDrawable(
                    root.context,
                    R.drawable.item_one_rounded
                ) as GradientDrawable

                shapeDrawable.setColor(mainColor)
                root.background = shapeDrawable

                introduce.text = data.title
                whatIsIt.text = data.question
                time.text = "${data.duration} min"
            }
        }
    }
}

object DataDiffCallBack : DiffUtil.ItemCallback<NewCourse>() {
    override fun areItemsTheSame(oldItem: NewCourse, newItem: NewCourse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewCourse, newItem: NewCourse): Boolean {
        return oldItem == newItem
    }
}


