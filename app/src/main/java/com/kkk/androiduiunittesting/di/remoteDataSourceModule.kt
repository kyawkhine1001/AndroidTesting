package com.kkk.androiduiunittesting.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kkk.androiduiunittesting.BuildConfig
import com.kkk.androiduiunittesting.network.ApiService
import com.kkk.androiduiunittesting.utility.Configs
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val remoteDatasourceModule = module(definition = {
    // provided web components
    single { createOkHttpClient() }
    // Fill property
    single<ApiService> { createWebService(get(), Configs.apiBaseURL) }
})

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    val builder = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
    builder.addInterceptor(httpLoggingInterceptor)
        builder.interceptors().add(getHeaderInterceptor())
    }
        return builder.build()
}

fun getHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Accept", "application/json")
        requestBuilder.header("language", Locale.getDefault().language)
        requestBuilder.header("deviceType", "android")
        requestBuilder.addHeader("Authorization", "Bearer ")
        chain.proceed(requestBuilder.build())
    }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}
