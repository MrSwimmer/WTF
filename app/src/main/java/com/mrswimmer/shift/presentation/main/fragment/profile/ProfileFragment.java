package com.mrswimmer.shift.presentation.main.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends BaseFragment implements ProfileFragmentView {
    @InjectPresenter
    ProfileFragmentPresenter presenter;

    @ProvidePresenter
    public ProfileFragmentPresenter presenter() {
        return new ProfileFragmentPresenter();
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_profile;
    }

}
