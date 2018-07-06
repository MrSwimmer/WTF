package com.bignerdranch.android.osm.data.paging

import android.support.v7.util.DiffUtil
import com.mrswimmer.shift.data.model.firebase.Task

class TaskDiffUtilCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.count == newItem.count
    }
}