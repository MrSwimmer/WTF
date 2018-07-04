package com.mrswimmer.shift.presentation.main.fragment.tasks;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.domain.interactor.SettingsService;

import javax.inject.Inject;

@InjectViewState
public class TasksFragmentPresenter extends MvpPresenter<TasksFragmentView> {
    @Inject
    SettingsService settingsService;

    public TasksFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
