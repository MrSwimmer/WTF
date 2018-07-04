package com.mrswimmer.shift.presentation.auth.fragment.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.ButterKnife;

public class SignInFragment extends BaseFragment implements SignInFragmentView {
    @InjectPresenter
    SignInFragmentPresenter presenter;

    @ProvidePresenter
    public SignInFragmentPresenter presenter() {
        return new SignInFragmentPresenter();
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_in;
    }

}
