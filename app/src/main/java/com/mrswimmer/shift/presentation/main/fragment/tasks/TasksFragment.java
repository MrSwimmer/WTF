package com.mrswimmer.shift.presentation.main.fragment.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.database.DatabaseError;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.model.firebase.Task;
import com.mrswimmer.shift.domain.utils.TaskDiffUtilCallback;
import com.mrswimmer.shift.presentation.base.BaseFragment;
import com.mrswimmer.shift.presentation.main.fragment.tasks.recycler.TaskAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TasksFragment extends BaseFragment implements TasksFragmentView {

    TaskAdapter taskAdapter = null;

    @InjectPresenter
    TasksFragmentPresenter presenter;

    @ProvidePresenter
    public TasksFragmentPresenter presenter() {
        return new TasksFragmentPresenter();
    }

    @BindView(R.id.tasks_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tasks_empty)
    TextView emptyText;
    @BindView(R.id.tasks_progress)
    ProgressBar progressBar;

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.setRecyclerData();
        progressBar.setVisibility(View.VISIBLE);
        Log.i("code", "setdata");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tasks;
    }


    @Override
    public void setAdapter(List<Task> tasks) {
        progressBar.setVisibility(View.INVISIBLE);
        if (taskAdapter == null) {
            Log.i("code", "first adapter");
            taskAdapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(taskAdapter);
        } else {
            Log.i("code", "update adapter");
            TaskDiffUtilCallback diffUtilCallback = new TaskDiffUtilCallback(taskAdapter.getData(), tasks);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
            taskAdapter.setData(tasks);
            diffResult.dispatchUpdatesTo(taskAdapter);
        }
    }


    @Override
    public void showErrorToast(DatabaseError e) {
        showToast(e.getMessage());
    }

    @Override
    public void setEmptyText(boolean empty) {
        if (empty)
            emptyText.setVisibility(View.VISIBLE);
        else
            emptyText.setVisibility(View.INVISIBLE);
    }
}
