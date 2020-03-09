package s.com.themoviedbupcoming.remote.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.koin.standalone.KoinComponent
import org.koin.standalone.getProperty

class PreferenceHelper : KoinComponent {
    private val contextApp: Context = getProperty(KEY_APPLICATION_CONTEXT)

    private fun defaultPrefs(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(contextApp)

    fun customPrefs(name: String): SharedPreferences =
        contextApp.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun getAccessTokenSocial() = defaultPrefs()[KEY_ACCESS_TOKEN_SOCIAL] ?: ""

    fun storeAccessTokenSocial(accessToken: String) {
        defaultPrefs()[KEY_ACCESS_TOKEN_SOCIAL] = accessToken
    }


    fun getAccessToken() = defaultPrefs()[KEY_ACCESS_TOKEN] ?: ""

    fun storeAccessToken(accessToken: String) {
        defaultPrefs()[KEY_ACCESS_TOKEN] = accessToken
    }

    fun getAccessTokenExpiry(): Long = defaultPrefs()[KEY_ACCESS_TOKEN_EXPIRY] ?: 0

    fun storeAccessTokenExpiry(expiry: Long) {
        defaultPrefs()[KEY_ACCESS_TOKEN_EXPIRY] = expiry
    }

    fun getUID() = defaultPrefs()[KEY_UID] ?: ""

    fun storeUID(uid: String) {
        defaultPrefs()[KEY_UID] = uid
    }

    fun getResetPasswordUlr() = defaultPrefs()[KEY_RESET_PASSWORD] ?: ""

    fun storeResetPasswordUlr(url: String) {
        defaultPrefs()[KEY_RESET_PASSWORD] = url
    }

    fun getInstagramAccessToken() = defaultPrefs()[KEY_ACCESS_INSTAGRAM] ?: ""

    fun storeInstagramAccessToken(accessToken: String) {
        defaultPrefs()[KEY_ACCESS_INSTAGRAM] = accessToken
    }

    fun isUserUpdate() = defaultPrefs()[KEY_SOCIAL] ?: false

    fun setIsUserUpdate(fromSocial: Boolean) {
        defaultPrefs()[KEY_SOCIAL] = fromSocial
    }

    fun getClient() = defaultPrefs()[KEY_CLIENT] ?: ""

    fun storeClient(uid: String) {
        defaultPrefs()[KEY_CLIENT] = uid
    }

    fun isFullProfile(): Boolean = defaultPrefs()[KEY_PROFILE_FILLED] ?: false

    fun setIsFullProfile(isFull: Boolean) {
        defaultPrefs()[KEY_PROFILE_FILLED] = isFull
    }

    fun getFCMToken() = defaultPrefs()[KEY_FCM_TOKEN] ?: ""

    fun storeFCMToken(token: String) {
        defaultPrefs()[KEY_FCM_TOKEN] = token
    }

    fun getUserID() = defaultPrefs()[KEY_USER_ID] ?: Long.MIN_VALUE

    fun storeUserID(uid: Long) {
        defaultPrefs()[KEY_USER_ID] = uid
    }

}