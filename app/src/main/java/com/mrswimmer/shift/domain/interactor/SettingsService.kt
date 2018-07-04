package com.mrswimmer.shift.domain.interactor

import android.content.SharedPreferences

class SettingsService(var sharedPreferences: SharedPreferences) {

    fun getEmail(): String {
        return sharedPreferences.getString("email", "error")
    }

    fun setEmail(email: String) {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }
}