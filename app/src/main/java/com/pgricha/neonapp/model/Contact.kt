package com.pgricha.neonapp.model

import com.google.gson.annotations.SerializedName

data class Contact (
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("value")
    var value: String?,
    @SerializedName("date")
    var date: String?
)