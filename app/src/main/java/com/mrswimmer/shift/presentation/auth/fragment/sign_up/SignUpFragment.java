package com.mrswimmer.shift.presentation.auth.fragment.sign_up;

import android.support.design.widget.TextInputEditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment implements SignUpFragmentView {
    @BindView(R.id.sign_up_email)
    TextInputEditText email;
    @BindView(R.id.sign_up_pass)
    TextInputEditText pass;
    @BindView(R.id.sign_up_repeat)
    TextInputEditText repeat;

    @InjectPresenter
    SignUpFragmentPresenter presenter;

    @ProvidePresenter
    public SignUpFragmentPresenter presenter() {
        return new SignUpFragmentPresenter();
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_up;
    }

    @OnClick(R.id.sign_up_back)
    void onBackClick() {
        presenter.backToSignIn();
    }

    @OnClick(R.id.sign_up_reg)
    void onRegClick() {
        if (isFullFields()) {
            if (isPassEquals())
                presenter.registration(email.getText().toString(), pass.getText().toString());
            else
                showToast("Пароли не совпадают");
        } else
            showToast("Заполните все поля");
    }

    private boolean isPassEquals() {
        return pass.getText().toString().equals(repeat.getText().toString());
    }

    boolean isFullFields() {
        return !email.getText().toString().equals("") && !pass.getText().toString().equals("") && !repeat.getText().toString().equals("");
    }

    @Override
    public void showErrorMessage(String message) {
        showToast(message);
    }
}
