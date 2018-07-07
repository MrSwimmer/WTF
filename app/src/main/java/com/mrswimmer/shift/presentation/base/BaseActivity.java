package com.mrswimmer.shift.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.domain.navigator.GlobalNavigator;
import com.mrswimmer.shift.domain.navigator.LocalNavigator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    @Inject
    @Local
    NavigatorHolder localNavigatorHolder;
    @Inject
    @Global
    NavigatorHolder globalNavigatorHolder;
    @Inject
    @Local
    Router localRouter;
    @Inject
    @Global
    Router globalRouter;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(this.savedInstanceState);
        Log.i("code", "create");
        injectDependencies();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //!!!do not fucking touch this!!!
        getWindow().getDecorView().post(() ->
        {
            if (savedInstanceState == null)
                localRouter.newRootScreen(Screens.TASKS_SCREEN);
        });
    }

    protected abstract int getContainerId();

    @Override
    protected void onResume() {
        super.onResume();
        localNavigatorHolder.setNavigator(new LocalNavigator(getSupportFragmentManager(), getContainerId()));
        globalNavigatorHolder.setNavigator(new GlobalNavigator(this));

    }

    protected abstract void injectDependencies();

    protected abstract int getLayoutId();

    @Override
    @StateStrategyType(OneExecutionStateStrategy.class)
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ОК",
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void showSnack(String message, View.OnClickListener snackOnClickListener) {
        Snackbar.make(getCurrentFocus(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showChoiceDialog(String title, String message, BaseFragment.DialogActionCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, id) -> callback.positiveAction(dialog))
                .setNegativeButton("Нет", (dialog, which) -> callback.negativeAction(dialog));
        AlertDialog alert = builder.create();
        alert.show();
    }
}
