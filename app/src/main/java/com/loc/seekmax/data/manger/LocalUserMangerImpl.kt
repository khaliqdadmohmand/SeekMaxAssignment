package com.loc.seekmax.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.loc.seekmax.domain.manager.LocalUserManger
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.util.Constants
import com.loc.seekmax.util.Constants.EMAIL
import com.loc.seekmax.util.Constants.FIRSTNAME
import com.loc.seekmax.util.Constants.IMAGE
import com.loc.seekmax.util.Constants.IS_AUTHENTICATED
import com.loc.seekmax.util.Constants.LASTNAME
import com.loc.seekmax.util.Constants.PHONE
import com.loc.seekmax.util.Constants.USERID
import com.loc.seekmax.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context
) : LocalUserManger {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun saveAuthState() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.IS_AUTHENTICATED] = true
        }
    }

    override fun readAuthState(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.IS_AUTHENTICATED] ?: false
        }
    }

    override suspend fun clearAuthState() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.IS_AUTHENTICATED] = false
        }
    }

    companion object {
        val EMAIL = stringPreferencesKey(Constants.EMAIL)
        val PHONE = stringPreferencesKey(Constants.PHONE)
        val FIRSTNAME = stringPreferencesKey(Constants.FIRSTNAME)
        val LASTNAME = stringPreferencesKey(Constants.LASTNAME)
        val IMAGE = stringPreferencesKey(Constants.IMAGE)
        val USERID = intPreferencesKey(Constants.USERID)
    }

    suspend fun saveUserToDataStore(user: User) {
        context.dataStore.edit {
            it[EMAIL] = user.email
            it[PHONE] = user.phone
            it[FIRSTNAME] = user.firstname
            it[LASTNAME] = user.lastname
            it[IMAGE] = user.image
            it[USERID] = user.id
        }
    }

    fun getUserFromDataStore() = context.dataStore.data.map {
        User(
            email = it[EMAIL]?:"",
            phone = it[PHONE]?:"",
            firstname = it[FIRSTNAME]?:"",
            lastname = it[LASTNAME]?:"",
            image = it[IMAGE]?:"",
            id = it[USERID]?:0,
            password = ""
        )
    }


    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }
}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    val IS_AUTHENTICATED = booleanPreferencesKey(Constants.IS_AUTHENTICATED)
}

