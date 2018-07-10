package com.mrswimmer.shift.presentation.main.fragment.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.data.model.firebase.Acc;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.BindView;


public class ProfileFragment extends BaseFragment implements ProfileFragmentView {

    @BindView(R.id.profile_email)
    TextView email;
    @BindView(R.id.profile_count)
    TextView count;
    @BindView(R.id.profile_wins)
    TextView wins;
    /*@BindView(R.id.profile_progress)
    ProgressBar progressBar;*/

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //progressBar.setVisibility(View.VISIBLE);
        showProgress();
        presenter.getUserData();
    }

    @Override
    public void setUserData(Acc acc) {
        //progressBar.setVisibility(View.INVISIBLE);
        hideProgress();
        email.setText(acc.getEmail());
        count.setText(String.valueOf(acc.getAmountOfTasks()));
        wins.setText(String.valueOf(acc.getWins()));
    }

    @Override
    public void showErrorMessage() {
        //progressBar.setVisibility(View.INVISIBLE);
        hideProgress();
        showToast("Ошибка загрузки данных");
    }
}
