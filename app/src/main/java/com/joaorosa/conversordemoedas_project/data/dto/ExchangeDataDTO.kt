package com.joaorosa.conversordemoedas_project.data.dto

data class ExchangeDataDTO(
    val `by`: String,
    val execution_time: Int,
    val from_cache: Boolean,
    val results: Results,
    val valid_key: Boolean
)