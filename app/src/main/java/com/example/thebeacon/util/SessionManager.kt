package com.example.thebeacon.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("thebeacon_prefs", Context.MODE_PRIVATE)

    fun saveAuth(idToken: String, refreshToken: String, uid: String, email: String) {
        prefs.edit()
            .putString(KEY_ID_TOKEN, idToken)
            .putString(KEY_REFRESH_TOKEN, refreshToken)
            .putString(KEY_UID, uid)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun getIdToken(): String? = prefs.getString(KEY_ID_TOKEN, null)
    fun getRefreshToken(): String? = prefs.getString(KEY_REFRESH_TOKEN, null)
    fun getUid(): String? = prefs.getString(KEY_UID, null)
    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    companion object {
        private const val KEY_ID_TOKEN = "id_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_UID = "uid"
        private const val KEY_EMAIL = "email"
    }
}
