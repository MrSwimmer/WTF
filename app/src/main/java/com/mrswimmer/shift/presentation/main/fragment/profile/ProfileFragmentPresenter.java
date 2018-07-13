package com.mrswimmer.shift.presentation.main.fragment.profile;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseError;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.data.api.req.TasksRequest;
import com.mrswimmer.shift.data.api.res.AccResult;
import com.mrswimmer.shift.data.model.firebase.Acc;
import com.mrswimmer.shift.domain.interactor.ApiService;
import com.mrswimmer.shift.domain.interactor.FireService;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@InjectViewState
public class ProfileFragmentPresenter extends MvpPresenter<ProfileFragmentView> {
    /*@Inject
    FireService fireService;*/
    @Inject
    ApiService apiService;

    public ProfileFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void getUserData() {

        apiService.getProfile(new TasksRequest(), new ApiService.ProfileCallback() {
            @Override
            public void onSuccess(AccResult acc) {
                getViewState().setUserData(acc.getData());
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showErrorMessage(e.getMessage());
            }
        });
        /*fireService.getUser(new FireService.UserCallback() {
            @Override
            public void onSuccess(@NotNull Acc acc) {
                getViewState().setUserData(acc);
            }

            @Override
            public void onError(@NotNull DatabaseError databaseError) {
                getViewState().showErrorMessage();
            }
        });*/
    }
}
