package com.mrswimmer.shift.di.utils

import android.support.v7.util.DiffUtil
import com.mrswimmer.shift.data.model.firebase.Task

class TaskDiffUtilCallback(private val oldList: List<Task>, private val newList: List<Task>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newTask = newList[newItemPosition]
        val oldTask = oldList[oldItemPosition]
        return newTask.id == oldTask.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}