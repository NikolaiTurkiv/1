package com.test.a1.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a1.data.network.response.NewsInfoResponse
import com.test.a1.domain.FootballUseCase
import com.test.a1.domain.OptionsUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsVewModel @Inject constructor(
    private val footballUseCase: FootballUseCase,
    private val optionsUseCase: OptionsUseCase,
) : ViewModel() {

    val wallpaper = optionsUseCase.backgroundWallpaper
    val isDarkTheme = optionsUseCase.isDarkTheme


    private val _newsListLD = MutableLiveData<List<NewsInfoResponse>>()
    val newsListLD: LiveData<List<NewsInfoResponse>>
        get() = _newsListLD

    fun getNews() {
        footballUseCase.news
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _newsListLD.postValue(it)
            }, {
                Log.d("ERROR", it.toString())
            })
    }
}
