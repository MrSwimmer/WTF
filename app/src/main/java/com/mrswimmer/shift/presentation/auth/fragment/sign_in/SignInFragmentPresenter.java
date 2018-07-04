package com.mrswimmer.shift.presentation.auth.fragment.sign_in;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.domain.interactor.SettingsService;

import javax.inject.Inject;

@InjectViewState
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {
    @Inject
    SettingsService settingsService;

    public SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
