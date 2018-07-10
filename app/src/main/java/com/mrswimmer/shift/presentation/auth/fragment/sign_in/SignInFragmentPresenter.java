package com.mrswimmer.shift.presentation.auth.fragment.sign_in;

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
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {

    @Inject
    @Local
    Router localRouter;
    @Inject
    @Global
    Router globalRouter;
    @Inject
    FireService fireService;

    SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void enter(String email, String pass) {
        fireService.signIn(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                getViewState().showErrorToast(e.getMessage());
            }
        });
    }

    void goToRegistration() {
        localRouter.navigateTo(Screens.SIGN_UP_SCREEN);
    }

    public void initUserAfterGoogleSignIn() {
        fireService.getUserId();
        globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
    }
}
