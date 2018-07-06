package com.bignerdranch.android.osm.presentation.notes.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mrswimmer.shift.R

class TaskViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var fio: TextView = v.findViewById(R.id.item_fio_fio)
    var yes: ImageView = v.findViewById(R.id.item_fio_yes)
    var no: ImageView = v.findViewById(R.id.item_fio_no)
}