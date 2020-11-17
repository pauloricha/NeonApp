package com.pgricha.neonapp.model

import com.google.gson.annotations.SerializedName

data class Profile (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("token")
    val token: String?
)