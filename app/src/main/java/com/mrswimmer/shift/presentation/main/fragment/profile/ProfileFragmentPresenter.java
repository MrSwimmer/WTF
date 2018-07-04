package com.mrswimmer.shift.presentation.main.fragment.profile;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.domain.interactor.SettingsService;

import javax.inject.Inject;

@InjectViewState
public class ProfileFragmentPresenter extends MvpPresenter<ProfileFragmentView> {
    @Inject
    SettingsService settingsService;

    public ProfileFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
