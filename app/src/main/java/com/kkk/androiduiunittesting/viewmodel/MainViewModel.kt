package com.kkk.androiduiunittesting.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.model.uimodel.RegisterUIModel
import com.kkk.androiduiunittesting.network.dataRepository.AuthRepository
import com.kkk.androiduiunittesting.network.rx.SchedulerProvider
import com.kkk.androiduiunittesting.utility.SingleLiveEvent

class MainViewModel(private val repository: AuthRepository, private val schedulerProvider: SchedulerProvider) : BaseViewModel() {

    val registerUIData  = MutableLiveData<RegisterUIModel>()
//    val registerUIData  = SingleLiveEvent<RegisterUIModel>()

    fun makeRegistration(name:String,phone:String,password:String){
        val occupationId = "5"
        val deviceId = "4f21fc64854ddd39"
        val firebaseToken = "4f21fc64854ddd394f21fc64854ddd394f21fc64854ddd394f21fc64854ddd39"
        val body = RegisterRequest(name,phone,occupationId,null,"",deviceId,firebaseToken)

        launch {
            repository.makeRegistration(body)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({ registerResponse ->
                    if (registerResponse.responseCode == 200) {
                        registerUIData.value = RegisterUIModel(data = registerResponse.data)
                    } else {
                        if (registerResponse.errors?.fullName != null){
                            registerUIData.value = RegisterUIModel(error = registerResponse.errors?.fullName)
                        }else if (registerResponse.errors?.phone != null){
                            registerUIData.value = RegisterUIModel(error = registerResponse.errors?.phone)
                        }
                    }
                }, { error ->
                    registerUIData.value = RegisterUIModel(error = error.localizedMessage)
                })
        }
    }
}
