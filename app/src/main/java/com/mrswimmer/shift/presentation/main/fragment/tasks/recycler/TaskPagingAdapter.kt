package com.bignerdranch.android.osm.presentation.notes.recycler

import android.annotation.SuppressLint
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mrswimmer.shift.App
import com.mrswimmer.shift.R
import com.mrswimmer.shift.data.model.firebase.Task
import com.mrswimmer.shift.domain.interactor.FireService
import javax.inject.Inject

class TaskPagingAdapter(diffCallback: DiffUtil.ItemCallback<Task>) : PagedListAdapter<Task, TaskViewHolder>(diffCallback) {

    @Inject
    lateinit var fireService: FireService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fio, parent, false)
        App.getComponent().inject(this)
        return TaskViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val fio = getItem(position)
        holder.fio.text = "${fio!!.first} ${fio!!.second} ${fio!!.third}"
        holder.yes.setOnClickListener({fireService.sendResult(fio.id, 1)})
        holder.no.setOnClickListener({fireService.sendResult(fio.id, 0)})
    }
}