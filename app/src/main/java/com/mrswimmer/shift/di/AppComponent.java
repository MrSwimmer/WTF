package com.mrswimmer.shift.di;

import com.bignerdranch.android.osm.di.module.FireModule;
import com.bignerdranch.android.osm.di.module.SharedPreferencesModule;
import com.mrswimmer.shift.presentation.auth.activity.AuthActivity;
import com.mrswimmer.shift.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.shift.presentation.auth.fragment.sign_in.SignInFragmentPresenter;
import com.mrswimmer.shift.presentation.auth.fragment.sign_up.SignUpFragment;
import com.mrswimmer.shift.presentation.auth.fragment.sign_up.SignUpFragmentPresenter;
import com.mrswimmer.shift.presentation.main.activity.MainActivity;
import com.mrswimmer.shift.presentation.main.activity.MainActivityPresenter;
import com.mrswimmer.shift.presentation.main.fragment.profile.ProfileFragment;
import com.mrswimmer.shift.presentation.main.fragment.profile.ProfileFragmentPresenter;
import com.mrswimmer.shift.presentation.main.fragment.settings.SettingsFragment;
import com.mrswimmer.shift.presentation.main.fragment.settings.SettingsFragmentPresenter;
import com.mrswimmer.shift.presentation.main.fragment.tasks.TasksFragment;
import com.mrswimmer.shift.presentation.main.fragment.tasks.TasksFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FireModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(ProfileFragment profileFragment);

    void inject(ProfileFragmentPresenter profileFragmentPresenter);

    void inject(SettingsFragment settingsFragment);

    void inject(SettingsFragmentPresenter settingsFragmentPresenter);

    void inject(TasksFragment tasksFragment);

    void inject(TasksFragmentPresenter tasksFragmentPresenter);

    void inject(MainActivity mainActivity);

    void inject(AuthActivity authActivity);

    void inject(SignInFragment signInFragment);

    void inject(SignUpFragment signUpFragment);

    void inject(SignUpFragmentPresenter signUpFragmentPresenter);

    void inject(SignInFragmentPresenter signInFragmentPresenter);
}
