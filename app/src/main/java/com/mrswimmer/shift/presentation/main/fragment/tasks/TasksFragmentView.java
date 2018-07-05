package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.arch.paging.PagedList;

import com.arellomobile.mvp.MvpView;

interface TasksFragmentView extends MvpView {

    void setAdapter(PagedList pagedList);
}
