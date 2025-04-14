package com.example.pokedex.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharePref {

    private const val LABEL_LOGIN_USER = "LOGIN-USER"
    private const val SHARED_LABEL = "POkEDEX-PREFERENCES"

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_LABEL, Context.MODE_PRIVATE)
    }

    fun getUser(context: Context): String? {
        return getPreference(context).getString(LABEL_LOGIN_USER, null)
    }

    fun saveUser(context: Context, value: String) {
        getPreference(context).edit {
            putString(LABEL_LOGIN_USER, value)
        }
    }

    fun clearSession(context: Context) {
        getPreference(context).edit {
            clear()
        }
    }
}