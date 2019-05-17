package ru.timekeeper.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import ru.timekeeper.data.*

class SharedPrefWrapper(context: Context) {

    private val sPref: SharedPreferences =
            context.getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)

    fun saveToken(token: String) {
        sPref.edit().run {
            putString(SHARED_PREF_TOKEN_KEY, token)
            apply()
        }
    }

    fun getToken(): String =
            sPref.getString(SHARED_PREF_TOKEN_KEY, "")

    fun saveUserId(userId: Int) {
        sPref.edit().run {
            putInt(SHARED_PREF_USER_ID_KEY, userId)
            apply()
        }
    }

    fun getUserId(): Int =
            sPref.getInt(SHARED_PREF_USER_ID_KEY, -1)

    fun setTokenValidation(isTokenValid: Boolean) =
            sPref.edit().run {
                putBoolean(SHARED_PREF_TOKEN_VALIDATION_KEY, isTokenValid)
                apply()
            }

    fun isTokenValid(): Boolean =
            sPref.getBoolean(SHARED_PREF_TOKEN_VALIDATION_KEY, false)

    fun savePercent(percent: Int) =
            sPref.edit().run {
                putInt(SHARED_PREF_PERCENT_KEY, percent)
                apply()
            }

    fun getPercent() =
            sPref.getInt(SHARED_PREF_PERCENT_KEY, -1)
}
