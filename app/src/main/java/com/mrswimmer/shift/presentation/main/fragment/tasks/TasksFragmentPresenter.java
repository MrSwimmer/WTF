package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.arch.paging.PagedList;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseError;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.model.firebase.Task;
import com.mrswimmer.shift.domain.interactor.FireService;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

@InjectViewState
public class TasksFragmentPresenter extends MvpPresenter<TasksFragmentView> {

    @Inject
    public FireService fireService;

    TasksFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecyclerData() {
        fireService.getTasks(new FireService.TasksCallBack() {
            @Override
            public void onSuccess(@NotNull List<Task> tasks) {
                getViewState().setAdapter(tasks);
                if(tasks.size()==0)
                    getViewState().setEmptyText(true);
                else
                    getViewState().setEmptyText(false);
            }

            @Override
            public void onError(@NotNull DatabaseError e) {
                getViewState().showErrorToast(e);
            }
        });
    }

}
