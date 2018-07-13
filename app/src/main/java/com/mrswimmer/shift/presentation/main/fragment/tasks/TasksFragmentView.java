package com.mrswimmer.shift.presentation.main.fragment.tasks;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.shift.data.model.firebase.Task;

import java.util.List;

interface TasksFragmentView extends MvpView {

    void setAdapter(List<Task> pagedList);

    void showErrorToast(Throwable e);

    void setEmptyText(boolean empty);

}
