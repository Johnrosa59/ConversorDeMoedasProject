package com.joaorosa.conversordemoedas_project.data.remote.interceptors

import com.joaorosa.conversordemoedas_project.util.Constantes
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val construtorRequisicao = chain.request().newBuilder()

        val urlAtual = chain.request().url
        val novaUrl = urlAtual.newBuilder()

        novaUrl.addQueryParameter("key", Constantes.API_KEY)

        val urlFinalEfetiva = novaUrl.build()
        construtorRequisicao.url(urlFinalEfetiva)


        println("URL FINAL SENDO ACESSADA: $urlFinalEfetiva")

        return chain.proceed(construtorRequisicao.build())
    }
}