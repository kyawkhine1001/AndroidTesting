package com.kkk.androiduiunittesting.network

import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.model.networkResponse.RegistrationResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("member/register")
    fun registerAccount(@Body body: RegisterRequest): Observable<RegistrationResponse>

}