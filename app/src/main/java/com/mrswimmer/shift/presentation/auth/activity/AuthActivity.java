package com.mrswimmer.shift.presentation.auth.activity;

import android.content.Intent;
import android.util.Log;

import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.presentation.base.BaseActivity;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class AuthActivity extends BaseActivity {

    @Inject
    @Local
    NavigatorHolder mNavigatorHolder;

    @Inject
    @Local
    Router router;

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
