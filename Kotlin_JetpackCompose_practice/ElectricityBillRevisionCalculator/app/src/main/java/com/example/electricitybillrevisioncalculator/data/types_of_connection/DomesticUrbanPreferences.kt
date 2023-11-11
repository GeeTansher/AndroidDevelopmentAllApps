package com.example.electricitybillrevisioncalculator.data.types_of_connection
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class DomesticUrbanPreferences(private val context: Context) {
    companion object {
        private val STRING1_KEY = stringPreferencesKey("string1")
        private val STRING2_KEY = stringPreferencesKey("string2")
        private val STRING3_KEY = stringPreferencesKey("string3")
        private val STRING4_KEY = stringPreferencesKey("string4")
    }

    // Initialize default values for the strings
    private val defaultString1 = "Hello"
    private val defaultString2 = "World"
    private val defaultString3 = "DataStore"
    private val defaultString4 = "Preferences"

    // Flow to observe changes in string1
    val string1Flow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STRING1_KEY] ?: defaultString1
    }

    // Flow to observe changes in string2
    val string2Flow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STRING2_KEY] ?: defaultString2
    }

    // Flow to observe changes in string3
    val string3Flow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STRING3_KEY] ?: defaultString3
    }

    // Flow to observe changes in string4
    val string4Flow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STRING4_KEY] ?: defaultString4
    }

    // Update string values and save to DataStore
    suspend fun updateStrings(string1: String, string2: String, string3: String, string4: String) {
        context.dataStore.edit { preferences: MutablePreferences->
            preferences[STRING1_KEY] = string1
            preferences[STRING2_KEY] = string2
            preferences[STRING3_KEY] = string3
            preferences[STRING4_KEY] = string4
        }
    }
}
