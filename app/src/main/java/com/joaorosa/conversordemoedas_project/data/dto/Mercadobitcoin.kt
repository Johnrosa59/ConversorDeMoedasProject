package com.joaorosa.conversordemoedas_project.data.dto

data class Mercadobitcoin(
    val buy: Int,
    val format: List<String>,
    val last: Int,
    val name: String,
    val sell: Int,
    val variation: Double
)