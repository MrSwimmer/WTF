package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.arch.paging.PagedList;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bignerdranch.android.osm.data.paging.TaskPositionalDataSource;
import com.mrswimmer.shift.App;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@InjectViewState
public class TasksFragmentPresenter extends MvpPresenter<TasksFragmentView> {

    TasksFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void setRecyclerData() {
        TaskPositionalDataSource positionalDataSource = new TaskPositionalDataSource();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(8)
                .build();
        PagedList pagedList = new PagedList.Builder(positionalDataSource, config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
        getViewState().setAdapter(pagedList);
    }

    class MainThreadExecutor implements Executor {
        @Override
        public void execute(@NonNull Runnable command) {
            new Handler(Looper.getMainLooper()).post(command);
        }
    }

}
