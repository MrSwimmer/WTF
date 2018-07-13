package com.mrswimmer.shift.domain.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.mrswimmer.shift.App
import com.mrswimmer.shift.domain.interactor.FireService
import javax.inject.Inject
import android.content.Intent.getIntent
import android.R.string.cancel
import android.app.NotificationManager
import android.content.Context
import com.mrswimmer.shift.data.api.req.Result
import com.mrswimmer.shift.data.api.res.TasksResult
import com.mrswimmer.shift.domain.interactor.ApiService
import org.greenrobot.eventbus.EventBus




class SendResultService : Service() {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var fireService: FireService

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        App.getComponent().inject(this)
        Log.i("code", "service create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val id = intent.getStringExtra("id")
        val result = intent.getIntExtra("result", 10)
        val count = intent.getIntExtra("num", 0)
                Log.i("code", "service start $id $result")
        //fireService.sendResult(id, result)
        var resultRes = Result(id, result)
        apiService.sendResult(resultRes, object : ApiService.ResultCallback {
            override fun onSuccess(status: TasksResult) {
                Log.i("code", "status ${status.status} ${status.data.size}")
            }

            override fun onError(e: Throwable) {
                Log.i("code", "${e.message}")
            }

        })
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(count)
        return super.onStartCommand(intent, flags, startId)
    }
}