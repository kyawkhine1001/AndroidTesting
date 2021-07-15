package com.kkk.androiduiunittesting.model.networkRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("fullName")
    @Expose
    var fullName: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("occupationId")
    @Expose
    var occupationId: String? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("partnerCode")
    @Expose
    var partnerCode: String? = null,

    @SerializedName("deviceId")
    @Expose
    var deviceId: String? = null,

    @SerializedName("firebaseToken")
    @Expose
    var firebaseToken: String? = null

)