package com.mrswimmer.shift.presentation.main.fragment.tasks.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bignerdranch.android.osm.presentation.notes.recycler.TaskViewHolder
import com.mrswimmer.shift.R
import com.mrswimmer.shift.data.model.firebase.Task
import com.mrswimmer.shift.domain.interactor.FireService

import java.util.ArrayList

import javax.inject.Inject

class TaskAdapter(tasks: MutableList<Task>) : RecyclerView.Adapter<TaskViewHolder>() {
    private val tasks = tasks

    @Inject
    lateinit var fireService: FireService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fio, parent, false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.fio.text = "${task!!.first} ${task!!.second} ${task!!.third}"
        holder.yes.setOnClickListener({fireService!!.sendResult(task.id, 1)})
        holder.no.setOnClickListener({fireService!!.sendResult(task.id, 0)})
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
