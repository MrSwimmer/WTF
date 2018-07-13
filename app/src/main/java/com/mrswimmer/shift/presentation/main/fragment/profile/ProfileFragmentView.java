package com.mrswimmer.shift.presentation.main.fragment.profile;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.shift.data.model.firebase.Acc;

interface ProfileFragmentView extends MvpView {
    void setUserData(Acc acc);
    void showErrorMessage(String message);
}
