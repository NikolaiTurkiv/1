package com.test.a1.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.a1.ui.entities.AttackDefence

data class DefenceInfoResponse(
    @SerializedName("Команда")
    @Expose
    val command: String,
    @SerializedName("Турнир")
    @Expose
    val tournament: String,
    @SerializedName("Удары з.и.")
    @Expose
    val kicksZI: Double,
    @SerializedName("Отборы з.и.")
    @Expose
    val selectionsZI: Double,
    @SerializedName("Перехваты з.и.")
    @Expose
    val interceptionsZI: Double,
    @SerializedName("Фолы з.и.")
    @Expose
    val falls: Double,
    @SerializedName("Офсайды з.и.")
    @Expose
    val offside: Double,
    @SerializedName("Рейтинг")
    @Expose
    val rating: Double,
): AttackDefence
