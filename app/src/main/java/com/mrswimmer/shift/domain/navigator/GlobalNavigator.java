package com.mrswimmer.shift.domain.navigator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.mrswimmer.shift.data.screen.Screens;
import com.mrswimmer.shift.presentation.auth.activity.AuthActivity;
import com.mrswimmer.shift.presentation.main.activity.MainActivity;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

public class GlobalNavigator implements Navigator {
    Activity activity;

    public GlobalNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void applyCommands(Command[] commands) {
        if (commands[0] instanceof Forward) {
            Intent intent;
            switch (((Forward) commands[0]).getScreenKey()) {
                case Screens.MAIN_ACTIVITY:
                    intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                    break;
                case Screens.AUTH_ACTIVITY:
                    intent = new Intent(activity, AuthActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                case Screens.SHARE:
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Присоединяйтесь к Name Checker! https://play.google.com/store/apps/details?id=com.mrswimmer.shift");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Поделиться");
                    intent = Intent.createChooser(intent, "С помощью");
                    activity.startActivity(intent);
                    break;
                case Screens.SET_MARK:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.mrswimmer.shift"));
                    activity.startActivity(intent);
                    break;
            }
        } else if (commands[0] instanceof BackTo) {
            activity.finish();
        }
    }
}
