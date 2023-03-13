package com.test.a1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a1.domain.FootballUseCase
import com.test.a1.domain.OptionsUseCase
import com.test.a1.ui.entities.TableDetail
import com.test.a1.ui.entities.TableTitle
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TournamentViewModel @Inject constructor(
    private val footballUseCase: FootballUseCase,
    private val optionsUseCase: OptionsUseCase,
) : ViewModel() {

    val wallpaper = optionsUseCase.backgroundWallpaper
    val isDarkTheme = optionsUseCase.isDarkTheme

    private val _detailTournamentListLD = MutableLiveData<List<TableDetail>>()
    val detailTournamentListLD: LiveData<List<TableDetail>>
        get() = _detailTournamentListLD

    fun getTournamentInfo(position: Int) {
        footballUseCase.tournament
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val list = mutableListOf<TableDetail>()
                list.add(TableTitle())
                list.addAll(it[position].data)
                 _detailTournamentListLD.postValue(list)
             }, {
                Log.d("ERROR", it.toString())
            })
    }
}
