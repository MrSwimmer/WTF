package com.mrswimmer.shift.presentation.auth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.domain.interactor.FireService;
import com.mrswimmer.shift.presentation.base.BaseActivity;
import com.mrswimmer.shift.presentation.main.activity.MainActivity;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class AuthActivity extends BaseActivity {

    @Inject
    @Global
    Router globalRouter;

    @Inject
    FireService fireService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(fireService.isEnter())
            globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int getContainerId() {
        return Screens.CONTAINER_AUTH;
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }
}
