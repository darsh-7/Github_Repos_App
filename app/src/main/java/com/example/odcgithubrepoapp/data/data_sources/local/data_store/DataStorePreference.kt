package com.example.odcgithubrepoapp.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class DataStorePreference(
    private val context: Context
) {
    companion object {
        private object PreferenceKeys {
            val isFirstTimeKey = booleanPreferencesKey("isFirstTime")
        }
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = ""
        )
    }

    suspend fun saveIsFirstTimeEnterApp(isFirstTime:Boolean){
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.isFirstTimeKey] = isFirstTime
        }
    }

    fun readIsFirstTimeEnterApp(): Flow<Boolean> {
        return flow {
            context.dataStore.data.first()[PreferenceKeys.isFirstTimeKey]
        }
    }
}