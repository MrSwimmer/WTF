package com.mrswimmer.shift;

import android.app.Application;

import com.bignerdranch.android.osm.di.module.SharedPreferencesModule;
import com.mrswimmer.shift.di.AppComponent;

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
