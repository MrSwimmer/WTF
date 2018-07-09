package com.mrswimmer.shift.domain.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.mrswimmer.shift.App
import com.mrswimmer.shift.domain.interactor.FireService
import javax.inject.Inject

class SendResultService : Service() {
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

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var id = intent!!.extras.getString("id", "error")
        var result = intent.extras.getInt("result", 10)
        Log.i("code", "service start $id $result")
        fireService.sendResult(id, result)
        return super.onStartCommand(intent, flags, startId)
    }
}