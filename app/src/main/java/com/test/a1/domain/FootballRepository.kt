package com.test.a1.domain

import com.test.a1.data.network.NetworkApi
import com.test.a1.data.network.response.AttackInfoResponse
import com.test.a1.data.network.response.DefenceInfoResponse
import com.test.a1.data.network.response.LeagueInfoResponse
import com.test.a1.data.network.response.NewsInfoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FootballRepository {
    fun getTournamentInfo(): Single<List<LeagueInfo>>

    fun getAttackStatistic(): Single<List<AttackInfoResponse>>

    fun getDefenceStatistic(): Single<List<DefenceInfoResponse>>

    fun getNews(): Single<List<NewsInfoResponse>>
}
