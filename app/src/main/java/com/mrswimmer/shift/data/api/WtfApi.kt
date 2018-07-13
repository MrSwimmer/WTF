package com.mrswimmer.shift.data.api

import com.mrswimmer.shift.data.api.req.Result
import com.mrswimmer.shift.data.api.req.TasksRequest
import com.mrswimmer.shift.data.api.res.ResultStatus
import com.mrswimmer.shift.data.api.res.TasksResult
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface WtfApi {
    @POST("result/")
    fun sendResult(@Body result: Result): Observable<TasksResult>

    @POST("tasks/")
    fun getTasks(@Body result: TasksRequest): Observable<TasksResult>
}