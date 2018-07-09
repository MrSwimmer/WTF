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

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var id = intent.getStringExtra("id")
        var result = intent.getIntExtra("result", 10)
        Log.i("code", "service start $id $result")
        fireService.sendResult(id, result)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(0)
        return super.onStartCommand(intent, flags, startId)
    }
}