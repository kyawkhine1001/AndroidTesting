package com.kkk.androiduiunittesting.network.rx

import com.kkk.androiduiunittesting.network.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}