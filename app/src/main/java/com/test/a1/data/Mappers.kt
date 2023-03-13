package com.test.a1.data

import com.test.a1.data.network.response.CommandDetailResponse
import com.test.a1.data.network.response.LeagueInfoResponse
import com.test.a1.domain.CommandDetailInfo
import com.test.a1.domain.LeagueInfo

fun CommandDetailResponse.toCommandDetailInfo(): CommandDetailInfo{
    return CommandDetailInfo(
        command, games, V, N, P, balls, point, percentPoint
    )
}


fun LeagueInfoResponse.toLeagueInfo(): LeagueInfo{

    val list = mutableListOf<CommandDetailInfo>()

    if(this.data.data1 != null){
        list.add(this.data.data1.toCommandDetailInfo())
    }
    if(this.data.data2 != null){
        list.add(this.data.data2.toCommandDetailInfo())
    }
    if(this.data.data3 != null){
        list.add(this.data.data3.toCommandDetailInfo())

    }
    if(this.data.data4 != null){
        list.add(this.data.data4.toCommandDetailInfo())

    }
    if(this.data.data5 != null){
        list.add(this.data.data5.toCommandDetailInfo())

    }
    if(this.data.data6 != null){
        list.add(this.data.data6.toCommandDetailInfo())

    }
    if(this.data.data7 != null){
        list.add(this.data.data7.toCommandDetailInfo())

    }
    if(this.data.data8 != null){
        list.add(this.data.data8.toCommandDetailInfo())

    }
    if(this.data.data9 != null){
        list.add(this.data.data9.toCommandDetailInfo())

    }
    if(this.data.data10 != null){
        list.add(this.data.data10.toCommandDetailInfo())

    }
    if(this.data.data11 != null){
        list.add(this.data.data11.toCommandDetailInfo())

    }
    if(this.data.data12 != null){
        list.add(this.data.data12.toCommandDetailInfo())

    }
    if(this.data.data13 != null){
        list.add(this.data.data13.toCommandDetailInfo())

    }
    if(this.data.data14 != null){
        list.add(this.data.data14.toCommandDetailInfo())

    }
    if(this.data.data15 != null){
        list.add(this.data.data15.toCommandDetailInfo())

    }
    if(this.data.data16 != null){
        list.add(this.data.data16.toCommandDetailInfo())

    }
    if(this.data.data17 != null){
        list.add(this.data.data17.toCommandDetailInfo())

    }
    if(this.data.data18 != null){
        list.add(this.data.data18.toCommandDetailInfo())

    }
    if(this.data.data19 != null){
        list.add(this.data.data19.toCommandDetailInfo())

    }
    if(this.data.data20 != null){
        list.add(this.data.data20.toCommandDetailInfo())

    }


    return LeagueInfo(
        name = this.name,
        dates = this.dates,
        category = this.category,
        players_count = this.players_count,
        icon = this.icon,
        data = list
    )
}