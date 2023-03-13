package com.test.a1.domain

import com.test.a1.ui.entities.TableDetail

data class CommandDetailInfo(
    val command: String,
    val games: Int,
    val V: Int,
    val N: Int,
    val P: Int,
    val balls: String,
    val point: Int,
    val percentPoint: String,
) : TableDetail