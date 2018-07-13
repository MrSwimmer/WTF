package com.mrswimmer.shift.data.api.req

class Result() {
    lateinit var accId: String
    lateinit var taskId: String
    var result = 0

    constructor(accId: String, taskId: String, result: Int) : this() {
        this.accId = accId
        this.taskId = taskId
        this.result = result
    }

    constructor(taskId: String, result: Int) : this() {
        this.taskId = taskId
        this.result = result
    }
}