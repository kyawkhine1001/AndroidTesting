package com.kkk.androiduiunittesting.model.networkResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationResponse {
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null

    @SerializedName("responseCode")
    @Expose
    var responseCode: Int? = null

    @SerializedName("responseDescription")
    @Expose
    var responseDescription: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("secretKey")
    @Expose
    var secretKey: String? = null

    @SerializedName("sessionToken")
    @Expose
    var sessionToken: String? = null

    @SerializedName("lifetime")
    @Expose
    var lifetime: Int? = null

    @SerializedName("data")
    @Expose
    var data: UserProfileData? = null

    @SerializedName("errors")
    @Expose
    var errors: Errors? = null
}