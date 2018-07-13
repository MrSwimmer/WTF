package com.mrswimmer.shift.data.api.res

import com.mrswimmer.shift.data.model.firebase.Task

class TasksResult {
    lateinit var status: String
    var data: List<Task> = mutableListOf()

    //var tasks: List<Task> = mutableListOf()
    constructor(status: String, data: List<Task>) {
        this.status = status
        this.data = data
    }
}