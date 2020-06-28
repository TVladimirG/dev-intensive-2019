package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {
    // это собсвенно объект - SharedPreferences

    // Ключи SharedPreferences
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val APP_THEME = "APP_THEME"

    // Получим постоянное хранилище - prefs: SharedPreferences
    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun saveAppTheme(theme: Int) {
        putValue(
            APP_THEME to theme
        )
    }

    fun getAppTheme(): Int {
        return try {
            prefs.getInt(
                APP_THEME, AppCompatDelegate.MODE_NIGHT_NO
            )
        } catch (e: Exception) {
            AppCompatDelegate.MODE_NIGHT_NO
        }
    }


    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(
                RATING to rating
            )
            putValue(
                RESPECT to respect
            )
            putValue(
                FIRST_NAME to firstName
            )
            putValue(
                LAST_NAME to lastName
            )
            putValue(
                ABOUT to about
            )
            putValue(
                REPOSITORY to repository
            )
        }
    }

    fun getProfile(): Profile = Profile(
        // Берем наш prefs и get значения, если их нет, то по умолчанию.
        rating = prefs.getInt(
            RATING, 0
        ),
        respect = prefs.getInt(
            RESPECT, 0
        ),
        firstName = prefs.getString(
            FIRST_NAME, ""
        )!!,
        lastName = prefs.getString(
            LAST_NAME, ""
        )!!,
        about = prefs.getString(
            ABOUT, ""
        )!!,
        repository = prefs.getString(
            REPOSITORY, ""
        )!!
    )


    private fun putValue(pair: Pair<String, Any>) {

        // Берем prefs.edit() и в него put наши пары
        val editor = prefs.edit()

        val (k, v) = pair
        when (v) {
            is Boolean -> editor.putBoolean(k, v)
            is String -> editor.putString(k, v)
            is Float -> editor.putFloat(k, v)
            is Long -> editor.putLong(k, v)
            is Int -> editor.putInt(k, v)

            else -> error("Только примитивные типы данных могут быть сохранены в Shared Preferences")
        }

        editor.apply()


        // Более простая запись
        /*
        with(prefs.edit()) {
            val (k, v) = pair
            when (v) {
                is String -> putString(k, v)
                is Int -> putInt(k, v)
                is Boolean -> putBoolean(k, v)
                is Long -> putLong(k, v)
                is Float -> putFloat(k, v)

                else -> error("Только примитивные типы данных могут быть сохранены в Shared Preferences")
            }
            apply()
        }

         */
    }
}