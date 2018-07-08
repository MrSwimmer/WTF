package com.mrswimmer.shift.data.screen;

import com.mrswimmer.shift.R;

import org.jetbrains.annotations.Nullable;

public interface Screens {
    //activity
    String MAIN_ACTIVITY = "main activity";
    String AUTH_ACTIVITY = "auth activity";

    //auth
    String SIGN_IN_SCREEN = "sign in screen";
    String SIGN_UP_SCREEN = "sign up screen";
    String INTRO_SCREEN = "intro screen";

    //main
    String TASKS_SCREEN = "tasks screen";
    String SETTINGS_SCREEN = "settings screen";
    String PROFILE_SCREEN = "profile screen";
    String ADD_SCREEN = "add screen";

    //action
    String SHARE = "share";
    String SET_MARK = "set mark";
    String SUPPORT_SCREEN = "support screen";

    //Containers
    int CONTAINER_AUTH = R.id.auth_container;
    int CONTAINER_MAIN = R.id.main_container;
}
