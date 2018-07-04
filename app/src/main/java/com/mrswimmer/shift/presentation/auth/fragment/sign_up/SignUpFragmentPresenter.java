package com.mrswimmer.shift.presentation.auth.fragment.sign_up;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.domain.interactor.SettingsService;

import javax.inject.Inject;

@InjectViewState
public class SignUpFragmentPresenter extends MvpPresenter<SignUpFragmentView> {
    @Inject
    SettingsService settingsService;

    public SignUpFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
