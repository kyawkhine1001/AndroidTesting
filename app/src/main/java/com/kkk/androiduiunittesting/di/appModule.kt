package com.kkk.androiduiunittesting.di

import com.kkk.androiduiunittesting.network.dataRepository.AuthRepository
import com.kkk.androiduiunittesting.network.dataRepository.AuthRepositoryImpl
import com.kkk.androiduiunittesting.network.rx.AndroidSchedulerProvider
import com.kkk.androiduiunittesting.network.rx.SchedulerProvider
import com.kkk.androiduiunittesting.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val homeModule = module(definition = {

    //provide data repository
    single<AuthRepository> { AuthRepositoryImpl(get()) }

//    ViewModel for Home
    viewModel { MainViewModel(get(), get()) }

})

val rxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { AndroidSchedulerProvider() }
}

val appModule = listOf(remoteDatasourceModule, rxModule, homeModule)
