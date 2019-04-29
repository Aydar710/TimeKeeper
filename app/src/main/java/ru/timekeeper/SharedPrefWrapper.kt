package ru.timekeeper

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

class SharedPrefWrapper(context: Context) {

    private val sPref: SharedPreferences =
            context.getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)

    fun saveTokenToPreferences(token: String) {
        sPref.edit().run {
            putString(SHARED_PREF_TOKEN_KEY, token)
            apply()
        }
    }

    fun getTokenFromPreferences(): String =
            sPref.getString(SHARED_PREF_TOKEN_KEY, "")

}
