package com.test.a1.data.network

import com.test.a1.data.network.response.AttackInfoResponse
import com.test.a1.data.network.response.DefenceInfoResponse
import com.test.a1.data.network.response.LeagueInfoResponse
import com.test.a1.data.network.response.NewsInfoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NetworkApi {

    companion object {
        private const val TOURNAMENT_TABLES = "data_tournament_tables.json"
        private const val STATISTIC_ATTACK = "data_statistic_attack.json"
        private const val STATISTIC_DEFENCE = "data_statistic_defense.json"
        private const val NEWS = "news.json"
    }

    @GET(TOURNAMENT_TABLES)
    fun getTournamentInfo(): Single<List<LeagueInfoResponse>>

    @GET(STATISTIC_ATTACK)
    fun getAttackStatistic(): Single<List<AttackInfoResponse>>

    @GET(STATISTIC_DEFENCE)
    fun getDefenceStatistic(): Single<List<DefenceInfoResponse>>

    @GET(NEWS)
    fun getNews(): Single<List<NewsInfoResponse>>

}
