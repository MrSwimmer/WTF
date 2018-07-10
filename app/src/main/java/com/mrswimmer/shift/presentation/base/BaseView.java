package com.mrswimmer.shift.presentation.base;

import android.view.View;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showToast(String message);
    void showDialog(String title, String message);
    void showChoiceDialog(String title, String message, BaseFragment.DialogActionCallback callback);
    void showSnack(String message, View.OnClickListener snackOnClickListener);
    void showProgress();
    void hideProgress();
}
