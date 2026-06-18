package com.joaorosa.conversordemoedas_project.data.remote

import com.joaorosa.conversordemoedas_project.data.dto.ExchangeDataDTO
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeAPI {

    @GET("/finance")
    suspend fun recoveryCoins() : Response<ExchangeDataDTO>
}