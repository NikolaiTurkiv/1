package com.test.a1.domain

interface SharedPreferencesRepository {
    var isDarkTheme: Boolean
    var backgroundWallPaper: Int
    fun saveBackground(wallpaper: Int)
    fun saveTheme(isDark: Boolean)
}