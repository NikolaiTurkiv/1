package com.test.a1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a1.data.network.response.NewsInfoResponse
import com.test.a1.domain.FootballUseCase
import com.test.a1.domain.SharedUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsVewModel @Inject constructor(
    private val footballUseCase: FootballUseCase,
    private val optionsUseCase: SharedUseCase,
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
