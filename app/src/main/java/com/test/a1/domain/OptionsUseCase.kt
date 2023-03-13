package com.test.a1.domain

import javax.inject.Inject

class OptionsUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    var isDarkTheme = sharedPreferencesRepository.isDarkTheme
    var backgroundWallpaper = sharedPreferencesRepository.backgroundWallPaper

    fun saveBackground(wallpaper: Int){
        sharedPreferencesRepository.saveBackground(wallpaper)
    }

    fun saveTheme(isDark: Boolean){
        sharedPreferencesRepository.saveTheme(isDark)
    }

}