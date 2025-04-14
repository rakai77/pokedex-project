package com.example.core.di

import androidx.room.Room
import com.example.core.data.local.AppDatabase
import com.example.core.data.remote.repository.PokemonRepositoryImpl
import com.example.core.data.remote.repository.UserRepositoryImpl
import com.example.core.data.remote.service.ApiService
import com.example.core.domain.repository.PokemonRepository
import com.example.core.domain.repository.UserRepository
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.domain.usecase.PokemonUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val coreModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single<PokemonRepository> { PokemonRepositoryImpl(get()) }

    single { PokemonUseCase(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_db").build()
    }
    single { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { AuthUseCase(get()) }
}