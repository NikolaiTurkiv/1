package com.test.a1.domain

import javax.inject.Inject

class SharedUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    var isDarkTheme = sharedPreferencesRepository.isDarkTheme
    var backgroundWallpaper = sharedPreferencesRepository.backgroundWallPaper
    val id = sharedPreferencesRepository.currentId

    fun saveBackground(wallpaper: Int){
        sharedPreferencesRepository.saveBackground(wallpaper)
    }

    fun saveTheme(isDark: Boolean){
        sharedPreferencesRepository.saveTheme(isDark)
    }

    fun saveId(id:String){
        sharedPreferencesRepository.saveID(id)
    }

}