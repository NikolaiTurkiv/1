package com.test.a1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a1.domain.FootballUseCase
import com.test.a1.domain.SharedUseCase
import com.test.a1.ui.entities.AttackDefence
import com.test.a1.ui.entities.AttackTitle
import com.test.a1.ui.entities.DefenceTitle
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AttackDefenceViewModel @Inject constructor(
    private val footballUseCase: FootballUseCase,
    private val optionsUseCase: SharedUseCase
) : ViewModel() {

    val wallpaper = optionsUseCase.backgroundWallpaper
    val isDarkTheme = optionsUseCase.isDarkTheme

    private val _attackListLD = MutableLiveData<List<AttackDefence>>()
    val attackListLD: LiveData<List<AttackDefence>>
        get() = _attackListLD

    private val _defenceListLD = MutableLiveData<List<AttackDefence>>()
    val defenceListLD: LiveData<List<AttackDefence>>
        get() = _defenceListLD

    fun getStatistic(){
        getDefenceStatistic()
        getAttackStatistic()
    }


    private fun getAttackStatistic() {
        footballUseCase.attackInfo
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val list = mutableListOf<AttackDefence>()
                list.add(AttackTitle())
                list.addAll(it)
                _attackListLD.postValue(list)
                Log.d("getAttackStatistic", it.toString())

            }, {
                Log.d("ERROR", it.toString())
            })
    }

    private fun getDefenceStatistic() {
        footballUseCase.defenceInfo
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val list = mutableListOf<AttackDefence>()
                list.add(DefenceTitle())
                list.addAll(it)
                _defenceListLD.postValue(list)
                Log.d("getDefenceStatistic", it.toString())
            }, {
                Log.d("ERROR", it.toString())
            })
    }

}