package com.mrswimmer.shift.presentation.main.fragment.profile;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;

import javax.inject.Inject;

@InjectViewState
public class ProfileFragmentPresenter extends MvpPresenter<ProfileFragmentView> {

    public ProfileFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
