package com.example.odcgithubrepoapp.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.odcgithubrepoapp.data.Constants.Companion.PREFERENCES_IS_FIRST_TIME
import com.example.odcgithubrepoapp.data.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStorePreference @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private object PreferenceKeys {
            val isFirstTimeKey = booleanPreferencesKey(PREFERENCES_IS_FIRST_TIME)
        }

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = PREFERENCES_NAME
        )
    }

    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.isFirstTimeKey] = isFirstTime
        }
    }

    suspend fun readIsFirstTimeEnterApp(): Boolean {
        return runBlocking {
            context.dataStore.data.first()[PreferenceKeys.isFirstTimeKey] ?: true
        }

    }
}