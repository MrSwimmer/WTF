package com.mrswimmer.shift.domain.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.shift.presentation.auth.fragment.sign_up.SignUpFragment;
import com.mrswimmer.shift.presentation.main.fragment.profile.ProfileFragment;
import com.mrswimmer.shift.presentation.main.fragment.settings.SettingsFragment;
import com.mrswimmer.shift.presentation.main.fragment.tasks.TasksFragment;

import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class LocalNavigator extends SupportFragmentNavigator {

    int currentContainer;

    public LocalNavigator(FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);
        currentContainer = containerId;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (currentContainer) {
            case Screens.CONTAINER_MAIN:
                return mainFragments(screenKey);
            case Screens.CONTAINER_AUTH:
                return authFragments(screenKey);
            default:
                    authFragments(screenKey);
        }
        return null;
    }

    private Fragment mainFragments(String screenKey) {
        switch (screenKey) {
            case Screens.PROFILE_SCREEN:
                return new ProfileFragment();
            case Screens.TASKS_SCREEN:
                return new TasksFragment();
            case Screens.SETTINGS_SCREEN:
                return new SettingsFragment();
            case Screens.ADD_SCREEN:
                return new AddFragment();
            default:
                return new TasksFragment();
        }
    }

    private Fragment authFragments(String screenKey) {
        switch (screenKey) {
            case Screens.SIGN_IN_SCREEN :
                return new SignInFragment();
            case Screens.SIGN_UP_SCREEN :
                return new SignUpFragment();
            default:
                return new SignInFragment();
        }
    }

    @Override
    protected void showSystemMessage(String message) {
    }

    @Override
    protected void exit() {
    }
}


