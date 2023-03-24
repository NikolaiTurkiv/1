package com.test.a1.domain

interface SharedPreferencesRepository {
    var isDarkTheme: Boolean
    var backgroundWallPaper: Int
    val currentId: String
    fun saveBackground(wallpaper: Int)
    fun saveTheme(isDark: Boolean)
    fun saveID(id : String)
}