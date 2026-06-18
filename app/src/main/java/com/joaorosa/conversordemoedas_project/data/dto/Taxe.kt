package com.joaorosa.conversordemoedas_project.data.dto

data class Taxe(
    val cdi: Double,
    val cdi_daily: Double,
    val daily_factor: Int,
    val date: String,
    val selic: Double,
    val selic_daily: Double
)