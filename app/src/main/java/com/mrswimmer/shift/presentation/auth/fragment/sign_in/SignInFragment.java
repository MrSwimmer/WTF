package com.mrswimmer.shift.presentation.auth.fragment.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInFragmentView {

    @BindView(R.id.sign_in_email)
    TextInputEditText email;
    @BindView(R.id.sign_in_pass)
    TextInputEditText pass;

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
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_in;
    }

    @OnClick(R.id.sign_in_enter)
    void onEnterClick() {
        if (isFullFields())
            presenter.enter(email.getText().toString(), pass.getText().toString());
        else
            showToast("Заполните все поля");
    }

    @OnClick(R.id.sign_in_reg)
    void onRegClick() {
        presenter.goToRegistration();
    }

    boolean isFullFields() {
        return !email.getText().toString().equals("") && !pass.getText().toString().equals("");
    }

    @Override
    public void showErrorToast(String message) {
        showToast(message);
    }
}
