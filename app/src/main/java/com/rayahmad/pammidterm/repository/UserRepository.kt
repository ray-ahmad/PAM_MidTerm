package com.rayahmad.pammidterm.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserRepository(private val context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val GITHUB_USERNAME_KEY = stringPreferencesKey("github_username")
        val NIM_KEY = stringPreferencesKey("nim")
        val EMAIL_KEY = stringPreferencesKey("email")
        val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    suspend fun registerUser(username: String, password: String, githubUsername: String, nim: String, email: String) {
        dataStore.edit {
            it[USERNAME_KEY] = username
            it[PASSWORD_KEY] = password
            it[GITHUB_USERNAME_KEY] = githubUsername
            it[NIM_KEY] = nim
            it[EMAIL_KEY] = email
        }
    }

    suspend fun loginUser(username: String, password: String): Boolean {
        val preferences = dataStore.data.first()
        val isSuccess = preferences[USERNAME_KEY] == username && preferences[PASSWORD_KEY] == password
        if (isSuccess) {
            setLoggedIn(true)
        }
        return isSuccess
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit {
            it[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map {
        it[IS_LOGGED_IN_KEY] ?: false
    }

    suspend fun logout() {
        setLoggedIn(false)
    }

    val userData: Flow<UserData> = dataStore.data.map {
        UserData(
            username = it[USERNAME_KEY] ?: "",
            password = it[PASSWORD_KEY] ?: "",
            githubUsername = it[GITHUB_USERNAME_KEY] ?: "",
            nim = it[NIM_KEY] ?: "",
            email = it[EMAIL_KEY] ?: ""
        )
    }
}

data class UserData(
    val username: String,
    val password: String,
    val githubUsername: String,
    val nim: String,
    val email: String
)
