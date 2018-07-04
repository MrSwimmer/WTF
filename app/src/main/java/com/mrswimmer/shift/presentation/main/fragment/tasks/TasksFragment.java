package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.ButterKnife;

public class TasksFragment extends BaseFragment implements TasksFragmentView {
    @InjectPresenter
    TasksFragmentPresenter presenter;

    @ProvidePresenter
    public TasksFragmentPresenter presenter() {
        return new TasksFragmentPresenter();
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tasks;
    }

}
