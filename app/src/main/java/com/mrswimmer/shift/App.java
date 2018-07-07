package com.mrswimmer.shift;

import android.app.Application;

import com.mrswimmer.shift.di.AppComponent;
import com.mrswimmer.shift.di.DaggerAppComponent;
import com.mrswimmer.shift.di.module.SharedPreferencesModule;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .build();
    }
}
