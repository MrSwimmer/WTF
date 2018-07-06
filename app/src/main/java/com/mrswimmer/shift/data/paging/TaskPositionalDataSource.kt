package com.bignerdranch.android.osm.data.paging

import android.arch.paging.PositionalDataSource
import android.provider.ContactsContract
import com.mrswimmer.shift.App
import com.mrswimmer.shift.data.model.firebase.Task
import com.mrswimmer.shift.data.model.req.Fio
import com.mrswimmer.shift.domain.interactor.FireService
import javax.inject.Inject

class TaskPositionalDataSource : PositionalDataSource<Task>() {
    @Inject
    lateinit var fireService: FireService

    init {
        App.getComponent().inject(this)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Task>) {
        fireService.loadRange(params, callback)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Task>) {
        fireService.loadFirstPage(params, callback)
    }
}