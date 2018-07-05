package com.mrswimmer.shift.presentation.auth.fragment.sign_up;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.di.qualifier.Global;
import com.mrswimmer.shift.di.qualifier.Local;
import com.mrswimmer.shift.domain.interactor.FireService;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignUpFragmentPresenter extends MvpPresenter<SignUpFragmentView> {

    @Inject
    @Local
    Router localRouter;
    @Inject
    @Global
    Router globalRouter;
    @Inject
    FireService fireService;

    public SignUpFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void backToSignIn() {
        localRouter.backTo(Screens.SIGN_IN_SCREEN);
    }

    void registration(String email, String pass) {
        fireService.signUp(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                Log.i("code", fireService.isSignedIn() + "");
                globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                getViewState().showErrorMessage(e.getMessage());
            }
        });
    }
}

