package com.kkk.androiduiunittesting

import android.app.Application
import com.kkk.androiduiunittesting.di.appModule
import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }
}
