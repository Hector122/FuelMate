package com.corps.fuelmate.di

import com.corps.fuelmate.FuelRepository
import com.corps.fuelmate.network.FuelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply {
               // if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                //}
            })
            build()
        }

        return Retrofit.Builder()
            .baseUrl("https://api.digital.gob.do/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideFuelApi(retrofit: Retrofit): FuelApi {
        return retrofit.create(FuelApi::class.java)
    }


    @Provides
    fun provideFuelRepository(fuelAPI: FuelApi): FuelRepository {
        return FuelRepository(fuelAPI)
    }
}
