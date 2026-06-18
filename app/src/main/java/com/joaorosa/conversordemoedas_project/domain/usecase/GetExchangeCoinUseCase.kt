package com.joaorosa.conversordemoedas_project.domain.usecase

import com.joaorosa.conversordemoedas_project.domain.model.Exchange
import com.joaorosa.conversordemoedas_project.domain.repository.CoinRepository
import javax.inject.Inject

class GetExchangeCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
)  {

    suspend operator fun invoke() : List<Exchange> {
        return try {
            coinRepository()
        } catch (erroRecoveryCoins: Exception){
            return emptyList()
        }
    }

}