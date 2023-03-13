package com.test.a1.domain

import javax.inject.Inject

class FootballUseCase @Inject constructor(
    private val repository: FootballRepository
) {
    val tournament = repository.getTournamentInfo()
    val attackInfo = repository.getAttackStatistic()
    val defenceInfo = repository.getDefenceStatistic()
    val news = repository.getNews()
}
