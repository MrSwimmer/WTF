package com.mrswimmer.shift.domain

import android.arch.paging.PositionalDataSource
import android.util.Log
import com.mrswimmer.shift.data.model.req.Fio
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class CallbackFabric {
    companion object {
        fun getFioFirstPageCallback(callback: PositionalDataSource.LoadInitialCallback<Fio>): DisposableSingleObserver<List<Fio>> {
            return object : DisposableSingleObserver<List<Fio>>() {
                override fun onSuccess(t: List<Fio>) {
                    Log.i("code", "first load range ${t.size}")
                    callback.onResult(t, 0)
                }

                override fun onError(e: Throwable) {
                    Log.i("code", "first error ${e.message}")
                }
            }
        }
    }
}