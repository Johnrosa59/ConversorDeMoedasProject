package com.joaorosa.conversordemoedas_project.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.conversordemoedas_project.domain.model.Exchange
import com.joaorosa.conversordemoedas_project.domain.usecase.GetExchangeCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val getExchangeCoinsUseCase: GetExchangeCoinsUseCase
) : ViewModel() {


    private val _resultConversion = MutableLiveData<Double>()
    val resultConversion: LiveData<Double> = _resultConversion

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Mantida a sua variável original 'price'
    private var price: Exchange? = null

    init {
        requestQuote()
    }

    private fun requestQuote() {
        viewModelScope.launch {
            try {
                val lista = getExchangeCoinsUseCase()
                if (lista.isNotEmpty()) {
                    price = lista.firstOrNull()
                } else {
                    _error.value = "Não foi possível carregar as cotações."
                }
            } catch (e: Exception) {
                _error.value = "Erro de conexão. Verifique sua internet."
            }
        }
    }

    // Mantidos os nomes originais das suas variáveis (valueTyped, selectedCoin, coin, priceCoin)
    fun calcConversion(valueTyped: Double, selectedCoin: String) {
        val coin = price

        if (coin == null) {
            _error.value = "Cotações ainda não carregadas. Aguarde um instante e tente novamente."
            return
        }

        val priceCoin = when (selectedCoin) {
            "Dólar" -> coin.dolarCoin
            "Euro" -> coin.euroCoin
            "Dólar Canadense" -> coin.canadianDolarCoin
            "Peso Argentino" -> coin.argentinePesoCoin
            else -> 0.0
        }


        println("MOEDA SELECIONADA: $selectedCoin")
        println("VALOR DA MOEDA VINDO DA API: $priceCoin")

        if (priceCoin > 0.0) {
            val resultado = valueTyped / priceCoin
            _resultConversion.postValue(resultado)
        } else {
            _error.value = "Moeda com valor inválido."
        }
    }
}