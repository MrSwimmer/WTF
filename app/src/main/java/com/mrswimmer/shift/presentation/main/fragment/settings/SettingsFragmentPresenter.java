package com.mrswimmer.shift.presentation.main.fragment.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.domain.interactor.FireService;

import javax.inject.Inject;

@InjectViewState
public class SettingsFragmentPresenter extends MvpPresenter<SettingsFragmentView> {

    SettingsFragmentPresenter() {
        App.getComponent().inject(this);
    }

}
