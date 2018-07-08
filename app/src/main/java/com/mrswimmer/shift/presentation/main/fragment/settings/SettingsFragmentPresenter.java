package com.mrswimmer.shift.presentation.main.fragment.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.domain.interactor.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SettingsFragmentPresenter extends MvpPresenter<SettingsFragmentView> {

    @Inject
    FireService fireService;
    @Inject
    @Global
    Router globalRouter;

    SettingsFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void signOut() {
        fireService.signOut();
        globalRouter.navigateTo(Screens.AUTH_ACTIVITY);
    }

    public void setMark() {
        globalRouter.navigateTo(Screens.SET_MARK);
    }

    public void goToSupport() {
        globalRouter.navigateTo(Screens.SUPPORT_SCREEN);
    }
}
