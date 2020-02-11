package rs.droidtech.githubapi.repository.util

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private const val APP_PREF_NAME = "APP_PREF_NAME"

class PreferenceProperty<T>(
    private val key: String,
    private val defaultValue: T,
    private val getter: SharedPreferences.(String, T) -> T,
    private val setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
) : ReadWriteProperty<Context, T> {

    companion object Key {
        const val DEFAULT_USER_KEY = "user"
    }

    override fun getValue(thisRef: Context, property: KProperty<*>): T =
        thisRef.getPreferences()
            .getter(key, defaultValue)

    override fun setValue(thisRef: Context, property: KProperty<*>, value: T) =
        thisRef.getPreferences()
            .edit()
            .setter(key, value)
            .apply()

    private fun Context.getPreferences(): SharedPreferences =
        getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)
}

fun stringPreference(
    key: String,
    defaultValue: String? = null
): ReadWriteProperty<Context, String?> =
    PreferenceProperty(
        key = key,
        defaultValue = defaultValue,
        getter = SharedPreferences::getString,
        setter = SharedPreferences.Editor::putString
    )