package com.joaorosa.conversordemoedas_project.data.dto

data class Bitstamp(
    val buy: Double,
    val format: List<String>,
    val last: Double,
    val name: String,
    val sell: Double,
    val variation: Double
)