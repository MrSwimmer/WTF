package com.mrswimmer.shift.presentation.main.activity;

import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.domain.interactor.SettingsService;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.di.qualifier.Local;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    MainActivityPresenter() {
        App.getComponent().inject(this);
    }

    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SettingsService settingsService;

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
        switch(menuItem.getItemId()) {
            case R.id.nav_tasks:
                router.replaceScreen(Screens.TASKS_SCREEN);
                break;
            case R.id.nav_settings:
                router.replaceScreen(Screens.SETTINGS_SCREEN);
                break;
            default:
        }
        menuItem.setChecked(true);
        getViewState().checkDrawerItem(menuItem);
    }

    public String getEmail() {
        return settingsService.getEmail();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.i("code", "firstattach");
        router.newRootScreen(Screens.TASKS_SCREEN);
    }

    public void share() {

    }
}
