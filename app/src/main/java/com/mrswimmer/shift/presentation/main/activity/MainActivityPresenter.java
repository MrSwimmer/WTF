package com.mrswimmer.shift.presentation.main.activity;

import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.domain.interactor.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    MainActivityPresenter() {
        App.getComponent().inject(this);
    }

    @Inject
    @Local
    Router localRouter;
    @Inject
    @Global
    Router globalRouter;
    @Inject
    FireService fireService;

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setCheckedItem(0);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Log.i("code", "select drawer item");
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                localRouter.replaceScreen(Screens.PROFILE_SCREEN);
                break;
            case R.id.nav_tasks:
                localRouter.replaceScreen(Screens.TASKS_SCREEN);
                break;
            case R.id.nav_settings:
                localRouter.replaceScreen(Screens.SETTINGS_SCREEN);
                break;
            default:
                localRouter.replaceScreen(Screens.TASKS_SCREEN);
                break;
        }
        menuItem.setChecked(true);
        getViewState().checkDrawerItem(menuItem);
    }

    void share() {
        globalRouter.navigateTo(Screens.SHARE);
    }

    public String getEmail() {
        return fireService.getEmail();
    }
}
