package com.mrswimmer.shift.domain.interactor;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SettingsService {
    SharedPreferences sharedPreferences;

    public SettingsService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    void setUserId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.apply();
    }

    String getUserId() {
        return sharedPreferences.getString("id", "error");
    }
}
