package com.bignerdranch.android.osm.data.paging

import android.support.v7.util.DiffUtil
import com.mrswimmer.shift.data.model.req.Fio

class FioDiffUtilCallback : DiffUtil.ItemCallback<Fio>() {
    override fun areItemsTheSame(oldItem: Fio, newItem: Fio): Boolean {
        return oldItem.emailCo == newItem.emailCo
    }

    override fun areContentsTheSame(oldItem: Fio, newItem: Fio): Boolean {
        return oldItem.first == newItem.first && oldItem.second == newItem.second && oldItem.third == newItem.third
    }
}