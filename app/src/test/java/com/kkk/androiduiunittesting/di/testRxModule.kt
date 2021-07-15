package com.kkk.androiduiunittesting.di

import com.kkk.androiduiunittesting.network.rx.SchedulerProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val testRxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { TestSchedulerProvider() }
}
val testAppModule = listOf<Module>(
        testRxModule,
        remoteDatasourceModule,
        homeModule
)