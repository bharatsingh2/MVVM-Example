package com.example.android.hackerearchpractice.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.hackerearchpractice.databinding.ListItemBinding
import com.example.android.hackerearchpractice.vo.Weather

class Adapter :
    ListAdapter<Weather, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather) =
            oldItem.time == newItem.time

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Weather, newItem: Weather) = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Weather) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

}