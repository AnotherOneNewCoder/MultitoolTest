package ru.zhogin.app.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.zhogin.app.data.datastore.data.SettingsData



private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
class DataStoreManager(val context: Context) {
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("change_lang")] = settingsData.changeLanguage
        }
    }
    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
            pref[stringPreferencesKey("change_lang")] ?: "en"
        )

    }

}