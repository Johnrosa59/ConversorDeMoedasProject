package com.joaorosa.conversordemoedas_project.domain.repository

import com.joaorosa.conversordemoedas_project.domain.model.Exchange

interface CoinRepository {

    operator suspend fun invoke(): List<Exchange>


}