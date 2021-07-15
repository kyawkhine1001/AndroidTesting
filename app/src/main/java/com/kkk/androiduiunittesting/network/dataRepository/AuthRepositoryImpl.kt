package com.kkk.androiduiunittesting.network.dataRepository

import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.network.ApiService
import com.kkk.androiduiunittesting.model.networkResponse.RegistrationResponse
import io.reactivex.Observable

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {
    override fun makeRegistration(body: RegisterRequest): Observable<RegistrationResponse> {
        return apiService.registerAccount(body)
    }

}