package com.joaorosa.conversordemoedas_project.di

import androidx.lifecycle.ViewModel
import com.joaorosa.conversordemoedas_project.data.remote.ExchangeAPI
import com.joaorosa.conversordemoedas_project.data.remote.interceptors.AuthInterceptor
import com.joaorosa.conversordemoedas_project.data.repository.CoinRepositoryImpl
import com.joaorosa.conversordemoedas_project.domain.repository.CoinRepository
import com.joaorosa.conversordemoedas_project.domain.usecase.GetExchangeCoinsUseCase
import com.joaorosa.conversordemoedas_project.util.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModulo {

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideExchangeAPI(retrofit: Retrofit): ExchangeAPI {
        return retrofit.create(ExchangeAPI::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCoinRepository(exchangeAPI: ExchangeAPI) : CoinRepository{
        return CoinRepositoryImpl(exchangeAPI)

    }

    @Provides
    fun provideExchangeCoinsUseCase(coinRepository: CoinRepository): GetExchangeCoinsUseCase{
        return GetExchangeCoinsUseCase(coinRepository)

    }

}