package com.mrswimmer.shift.presentation.main.fragment.settings;

import android.content.DialogInterface;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsFragmentView {
    @InjectPresenter
    SettingsFragmentPresenter presenter;

    @ProvidePresenter
    public SettingsFragmentPresenter presenter() {
        return new SettingsFragmentPresenter();
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_settings;
    }

    @OnClick(R.id.settings_sign_out)
    void onSignOutClick() {
        showChoiceDialog("Выход из аккаунта", "Вы действительно хотите выйти из аккаунта?", new DialogActionCallback() {
            @Override
            public void positiveAction(DialogInterface dialog) {
                presenter.signOut();
            }

            @Override
            public void negativeAction(DialogInterface dialog) {
                dialog.cancel();
            }
        });
    }

    @OnClick(R.id.settings_set_mark_in_play)
    void onSetMarkClick() {
        presenter.setMark();
    }

    @OnClick(R.id.settings_version)
    void onVersionClick() {
        showDialog("О версии 1.0", "Это первая версия, вроде работает. Если заметили что-то подозрительное, то беспощадно долбите в саппорт. Ссылочка ниже");
    }

    @OnClick(R.id.settings_app)
    void onAppClick() {
        showDialog("О приложении", "Сейчас лень писать, попозже что-нибудь накидаю");
    }

    @OnClick(R.id.settings_support)
    void onSupportClick() {
        presenter.goToSupport();
    }
}
