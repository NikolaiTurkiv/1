package com.test.a1.data

import com.test.a1.data.network.NetworkApi
import com.test.a1.data.network.PhoneInfoRequest
import com.test.a1.data.network.response.*
import com.test.a1.domain.FootballRepository
import com.test.a1.domain.LeagueInfo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FootballRepositoryImpl @Inject constructor(
    val network: NetworkApi,
    val splashApi: SplashApi
) : FootballRepository {
    override fun getTournamentInfo(): Single<List<LeagueInfo>> {
        return network.getTournamentInfo().map { list ->
            list.map { it.toLeagueInfo() }
        }
    }

    override fun getAttackStatistic(): Single<List<AttackInfoResponse>> {
        return network.getAttackStatistic()
    }

    override fun getDefenceStatistic(): Single<List<DefenceInfoResponse>> {
        return network.getDefenceStatistic()
    }

    override fun getNews(): Single<List<NewsInfoResponse>> {
        return network.getNews()
    }

    override fun fetchPhoneData(
        id: String,
        locale: String,
        phoneModel: String
    ): Single<SplashResponse> {
        return splashApi.fetchPhoneStatus(PhoneInfoRequest(
            phoneName = phoneModel, locale = locale, id = id
        ))
    }

}