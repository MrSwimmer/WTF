package com.bignerdranch.android.osm.data.paging

import android.arch.paging.PositionalDataSource
import android.provider.ContactsContract
import com.mrswimmer.shift.App
import com.mrswimmer.shift.data.model.req.Fio
import com.mrswimmer.shift.domain.interactor.FireService
import javax.inject.Inject

class FioPositionalDataSource() : PositionalDataSource<Fio>() {
    @Inject
    lateinit var fireService: FireService;

    var type = 0
    var afterSleep = false
    var beginPeriod: Long = 0

    constructor(afterSleep: Boolean) : this() {
        type = 1
        this.afterSleep = afterSleep
    }

    constructor(beginPeriod: Long) : this() {
        type = 2
        this.beginPeriod = beginPeriod
    }

    constructor(beginPeriod: Long, afterSleep: Boolean) : this() {
        type = 3
        this.afterSleep = afterSleep
        this.beginPeriod = beginPeriod
    }

    init {
        App.getComponent().inject(this)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Fio>) {
        fireService.loadRange(params, callback)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Fio>) {
        fireService.loadFirstPage(params, callback)
    }
}