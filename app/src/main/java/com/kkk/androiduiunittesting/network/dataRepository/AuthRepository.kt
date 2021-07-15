package com.kkk.androiduiunittesting.network.dataRepository

import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.model.networkResponse.RegistrationResponse
import io.reactivex.Observable

interface AuthRepository {
    fun makeRegistration(body: RegisterRequest):Observable<RegistrationResponse>
}