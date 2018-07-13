package com.mrswimmer.shift.data.api.req

class TasksRequest() {
    lateinit var accId: String

    constructor(accId: String) : this() {
        this.accId = accId
    }
}