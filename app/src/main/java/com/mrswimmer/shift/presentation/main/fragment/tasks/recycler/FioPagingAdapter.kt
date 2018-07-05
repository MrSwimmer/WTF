package com.bignerdranch.android.osm.presentation.notes.recycler

import android.annotation.SuppressLint
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrswimmer.shift.App
import com.mrswimmer.shift.R
import com.mrswimmer.shift.data.model.req.Fio

class FioPagingAdapter(diffCallback: DiffUtil.ItemCallback<Fio>) : PagedListAdapter<Fio, FioViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FioViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fio, parent, false)
        App.getComponent().inject(this)
        return FioViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FioViewHolder, position: Int) {
        val fio = getItem(position)
        holder.fio.text = "${fio!!.first} ${fio!!.second} ${fio!!.third}"
        holder.yes.setOnClickListener({})
    }
}