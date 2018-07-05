package com.mrswimmer.shift.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mrswimmer.shift.domain.interactor.SettingsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SettingsService settingsService(SharedPreferences sharedPreferences) {
        return new SettingsService(sharedPreferences);
    }
}
