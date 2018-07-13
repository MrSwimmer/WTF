package com.mrswimmer.shift.presentation.main.fragment.tasks.recycler

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bignerdranch.android.osm.presentation.notes.recycler.TaskViewHolder
import com.mrswimmer.shift.App
import com.mrswimmer.shift.R
import com.mrswimmer.shift.data.api.req.Result
import com.mrswimmer.shift.data.api.res.TasksResult
import com.mrswimmer.shift.data.model.firebase.Task
import com.mrswimmer.shift.domain.interactor.ApiService
import com.mrswimmer.shift.domain.interactor.FireService
import com.mrswimmer.shift.domain.utils.TaskDiffUtilCallback

import javax.inject.Inject

class TaskAdapter(tasks: MutableList<Task>) : RecyclerView.Adapter<TaskViewHolder>() {
    private var tasks = tasks

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var fireService: FireService
    @Inject
    lateinit var apiService: ApiService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fio, parent, false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        Log.i("code", "task $task}")
        holder.fio.text = "${task!!.first} ${task!!.second} ${task!!.third}"
        holder.yes.setOnClickListener({
            //fireService!!.sendResult(task.id, 1)
            Log.i("code", " null id ${task.id == null}")
            val result = Result(task.id, 1)
            apiService.sendResult(result, object : ApiService.ResultCallback {
                override fun onSuccess(tasksResult: TasksResult) {
                    Log.i("code", "result ${tasksResult.status}")
                    Log.i("code", "result ${tasksResult.data.size}")
                    //tasks.remove(task)
                    val diffUtilCallback = TaskDiffUtilCallback(getData(), tasksResult.data)
                    val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                    setData(tasksResult.data as MutableList<Task>)
                    diffResult.dispatchUpdatesTo(this@TaskAdapter)
                    //setData(tasksResult.tasksResult as MutableList<Task>)
                }

                override fun onError(e: Throwable) {
                    Log.i("code", "result error ${e.message}")
                }

            })
        })
        holder.no.setOnClickListener({ fireService!!.sendResult(task.id, 0) })
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun getData(): MutableList<Task> {
        return tasks
    }

    fun setData(tasks: MutableList<Task>) {
        this.tasks = tasks
    }
}
