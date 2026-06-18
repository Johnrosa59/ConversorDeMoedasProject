package com.joaorosa.conversordemoedas_project.data.repository

import com.joaorosa.conversordemoedas_project.data.remote.ExchangeAPI
import com.joaorosa.conversordemoedas_project.domain.repository.CoinRepository
import com.joaorosa.conversordemoedas_project.domain.model.Exchange
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val exchangeapi: ExchangeAPI
) : CoinRepository {

    override suspend fun invoke(): List<Exchange> {
        return try {
            val response = exchangeapi.recoveryCoins()

            if (response.isSuccessful && response.body() != null) {
                val resultAPIDTO = response.body()


                val dolarCoin = resultAPIDTO?.results?.currencies?.USD?.sell ?: 0.0
                val euroCoin = resultAPIDTO?.results?.currencies?.EUR?.sell ?: 0.0
                val canadianDolarCoin = resultAPIDTO?.results?.currencies?.CAD?.buy ?: 0.0
                val argentinePesoCoin = if ((resultAPIDTO?.results?.currencies?.ARS?.buy ?: 0.0) > 0.0) {
                    resultAPIDTO?.results?.currencies?.ARS?.buy ?: 0.006
                } else {
                    0.006
                }

                val exchangeCoins = mutableListOf<Exchange>()
                exchangeCoins.add(
                    Exchange(dolarCoin, euroCoin, canadianDolarCoin, argentinePesoCoin)
                )

                println("teste, if")
                exchangeCoins
            }else {
                println("teste, else")
                println("REQUISIÇÃO FALHOU: Código ${response.code()} - Mensagem: ${response.message()}")
                emptyList()
            }
        }catch (erroRecoveryCoin: Exception){
            println("ERRO DA API: ${erroRecoveryCoin.message}")
            emptyList()
        }
    }
}