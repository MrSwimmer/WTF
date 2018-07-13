package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseError;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.api.req.TasksRequest;
import com.mrswimmer.shift.data.api.res.TasksResult;
import com.mrswimmer.shift.data.model.firebase.Task;
import com.mrswimmer.shift.domain.interactor.ApiService;
import com.mrswimmer.shift.domain.interactor.FireService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class TasksFragmentPresenter extends MvpPresenter<TasksFragmentView> {

    @Inject
    public FireService fireService;
    @Inject
    public ApiService apiService;

    TasksFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecyclerData() {
        apiService.getTasks(new TasksRequest(), new ApiService.ResultCallback() {
            @Override
            public void onSuccess(TasksResult result) {
                Log.i("code", result.status + result.getData().size());
                getViewState().setAdapter(result.getData());
                if (result.getData().size() == 0)
                    getViewState().setEmptyText(true);
                else
                    getViewState().setEmptyText(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", e.getMessage());
                getViewState().showErrorToast(e);
            }
        });

        
        /*fireService.getTasks(new FireService.TasksCallBack() {
            @Override
            public void onSuccess(@NotNull List<Task> tasks) {
                getViewState().setAdapter(tasks);
                if (tasks.size() == 0)
                    getViewState().setEmptyText(true);
                else
                    getViewState().setEmptyText(false);
            }

            @Override
            public void onError(@NotNull DatabaseError e) {
                getViewState().showErrorToast(e);
            }
        });*/

    }

}
