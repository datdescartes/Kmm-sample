package me.dat.kmp.shared.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import me.dat.kmp.shared.domain.model.AppSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsRepository : KoinComponent {
    companion object {
        val boolFieldKey = booleanPreferencesKey("boolField")
        val stringFieldKey = stringPreferencesKey("stringField")
        val intFieldKey = intPreferencesKey("intField")
    }

    private val pref: DataStore<Preferences> by inject()

    val settings: Flow<AppSettings> = pref.data.map {
        AppSettings(
            boolField = it[boolFieldKey] ?: false,
            stringField = it[stringFieldKey] ?: "",
            intField = it[intFieldKey] ?: 0,
        )
    }

    suspend fun saveSettings(
        boolField: Boolean,
        stringField: String,
        intField: Int,
    ) {
        pref.edit {
            it[boolFieldKey] = boolField
            it[stringFieldKey] = stringField
            it[intFieldKey] = intField
        }
    }
}