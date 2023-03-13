package com.test.a1.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommandDetailResponse(
    @SerializedName("Команда")
    @Expose
    val command: String,
    @SerializedName("Игры")
    @Expose
    val games: Int,
    @SerializedName("В")
    @Expose
    val V: Int,
    @SerializedName("Н")
    @Expose
    val N: Int,
    @SerializedName("П")
    @Expose
    val P: Int,
    @SerializedName("Мячи")
    @Expose
    val balls: String,
    @SerializedName("Очки")
    @Expose
    val point: Int,
    @SerializedName("% очков")
    @Expose
    val percentPoint: String,
)
