package com.test.a1.domain

import com.test.a1.data.network.response.SplashResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FootballUseCase @Inject constructor(
    private val repository: FootballRepository
) {
    val tournament = repository.getTournamentInfo()
    val attackInfo = repository.getAttackStatistic()
    val defenceInfo = repository.getDefenceStatistic()
    val news = repository.getNews()

    fun fetchPhoneData(id: String, locale: String, phoneModel: String): Single<SplashResponse> {
        return repository.fetchPhoneData(id, locale, phoneModel)
    }
}
