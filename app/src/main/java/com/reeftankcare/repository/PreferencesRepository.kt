package com.reeftankcare.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")

@Singleton
class PreferencesRepository @Inject constructor(
    @ApplicationContext context: Context,
) {

    val storedQuery: Flow<String>

    private val dataStore: DataStore<Preferences>

    init {
        dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("settings")
        }
        storedQuery = dataStore.data.map {
            it[SEARCH_QUERY_KEY] ?: ""
        }.distinctUntilChanged()
    }

    suspend fun setStoredQuery(query: String) {
        dataStore.edit {
            it[SEARCH_QUERY_KEY] = query
        }
    }
}