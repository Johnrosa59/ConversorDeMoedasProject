package com.joaorosa.conversordemoedas_project.data.dto

data class Results(
    val available_sources: List<String>,
    val bitcoin: Bitcoin,
    val currencies: Currencies,
    val stocks: Stocks,
    val taxes: List<Taxe>
)