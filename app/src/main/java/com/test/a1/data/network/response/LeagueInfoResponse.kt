package com.test.a1.data.network.response

data class LeagueInfoResponse(
    val name: String,
    val dates: String,
    val category: String,
    val players_count: Int,
    val icon: String,
    val data: CommandListDetailResponse
)