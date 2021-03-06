package com.mrswimmer.shift.di;

import com.mrswimmer.shift.di.module.APIModule;
import com.mrswimmer.shift.di.module.FireModule;
import com.mrswimmer.shift.di.module.NavigatorModule;
import com.mrswimmer.shift.di.module.SharedPreferencesModule;
import com.mrswimmer.shift.domain.interactor.ApiService;
import com.mrswimmer.shift.domain.interactor.FireService;
import com.mrswimmer.shift.domain.service.FCMService;
import com.mrswimmer.shift.domain.service.SendResultService;
import com.mrswimmer.shift.presentation.auth.activity.AuthActivity;
import com.mrswimmer.shift.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.shift.presentation.auth.fragment.sign_in.SignInFragmentPresenter;
import com.mrswimmer.shift.presentation.auth.fragment.sign_up.SignUpFragment;
import com.mrswimmer.shift.presentation.auth.fragment.sign_up.SignUpFragmentPresenter;
import com.mrswimmer.shift.presentation.main.activity.MainActivity;
import com.mrswimmer.shift.presentation.main.activity.MainActivityPresenter;
import com.mrswimmer.shift.presentation.main.fragment.profile.ProfileFragment;
import com.mrswimmer.shift.presentation.main.fragment.profile.ProfileFragmentPresenter;
import com.mrswimmer.shift.presentation.main.fragment.settings.report.ReportActivity;
import com.mrswimmer.shift.presentation.main.fragment.settings.SettingsFragment;
import com.mrswimmer.shift.presentation.main.fragment.settings.SettingsFragmentPresenter;
import com.mrswimmer.shift.presentation.main.fragment.tasks.TasksFragment;
import com.mrswimmer.shift.presentation.main.fragment.tasks.TasksFragmentPresenter;
import com.mrswimmer.shift.presentation.main.fragment.tasks.recycler.TaskAdapter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FireModule.class, NavigatorModule.class, SharedPreferencesModule.class, APIModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(AuthActivity authActivity);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(SignUpFragment signUpFragment);

    void inject(SignUpFragmentPresenter signUpFragmentPresenter);

    void inject(SignInFragmentPresenter signInFragmentPresenter);

    void inject(SignInFragment signInFragment);

    void inject(ProfileFragmentPresenter profileFragmentPresenter);

    void inject(ProfileFragment profileFragment);

    void inject(SettingsFragmentPresenter settingsFragmentPresenter);

    void inject(SettingsFragment settingsFragment);

    void inject(TasksFragment tasksFragment);

    void inject(TasksFragmentPresenter tasksFragmentPresenter);

    void inject(FireService fireService);

    void inject(TaskAdapter taskAdapter);

    void inject(ReportActivity reportActivity);

    void inject(SendResultService sendResultService);

    void inject(FCMService fcmService);

    void inject(ApiService apiService);
}
