package com.reeftankcare.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val SHARED_PREF_FILE = "sharedPrefFile"
private const val USER_PREF_FILE = "userPrefFile"
private const val IS_FIRST_OPEN = "isFirstOpen"

object SharedPreferenceRepository {
    private var sharedPreferences: SharedPreferences? = null
    private var userPreferences: SharedPreferences? = null

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE)
    }

    fun setIsFirstOpen(isFirstOpen: Boolean) {
        sharedPreferences?.edit {
            putBoolean(IS_FIRST_OPEN, isFirstOpen)
        }
    }

    fun isFirstOpen(): Boolean {
        return sharedPreferences?.getBoolean(IS_FIRST_OPEN, true) ?: true
    }

    fun clearUserPreferences() {
        userPreferences?.edit {
            clear()
        }
    }
}