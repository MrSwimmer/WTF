package com.mrswimmer.shift.data.api.res

import com.mrswimmer.shift.data.model.firebase.Acc
import com.mrswimmer.shift.data.model.firebase.Task

class AccResult() {
    lateinit var status: String
    lateinit var data: Acc

    constructor(status: String, data: Acc) : this() {
        this.status = status
        this.data = data
    }
}