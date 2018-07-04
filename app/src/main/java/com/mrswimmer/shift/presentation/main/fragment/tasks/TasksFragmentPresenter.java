package com.mrswimmer.shift.presentation.main.fragment.tasks;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;

import javax.inject.Inject;

@InjectViewState
public class TasksFragmentPresenter extends MvpPresenter<TasksFragmentView> {

    public TasksFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
