package com.test.a1.domain

import com.test.a1.data.network.response.CommandListDetailResponse

data class LeagueInfo(
    val name: String,
    val dates: String,
    val category: String,
    val players_count: Int,
    val icon: String,
    val data: List<CommandDetailInfo>
)