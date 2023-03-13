package com.test.a1.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.a1.ui.entities.AttackDefence

data class AttackInfoResponse(
    @SerializedName("Команда")
    @Expose
    val command: String,
    @SerializedName("Турнир")
    @Expose
    val tournament: String,
    @SerializedName("Удары з.и.")
    @Expose
    val kickZI: Double,
    @SerializedName("Удары ВСтв з.и.")
    @Expose
    val kickVSTV: Double,
    @SerializedName("Дриблинг з.и.")
    @Expose
    val dribblingZI: Double,
    @SerializedName("Дано фолов з.и.")
    @Expose
    val failsZI: Double,
    @SerializedName("Рейтинг")
    @Expose
    val rating: Double,
): AttackDefence
