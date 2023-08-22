package com.reeftankcare.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.reeftankcare.utilits.Constants.IS_FIRST_OPEN
import com.reeftankcare.utilits.Constants.SHARED_PREF_FILE
import com.reeftankcare.utilits.Constants.USER_PREF_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences
    private val userPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE)
    }

    fun setIsFirstOpen(isFirstOpen: Boolean) {
        sharedPreferences.edit { putBoolean(IS_FIRST_OPEN, isFirstOpen) }
    }

    fun isFirstOpen(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_OPEN, true)
    }

    fun clearUserPreferences() {
        userPreferences.edit {
            clear()
        }
    }
}