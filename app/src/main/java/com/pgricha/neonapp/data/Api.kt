package com.pgricha.neonapp.data

import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.model.Profile
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("getProfile")
    suspend fun getProfile(): Response<Profile>

    @GET("getContacts")
    suspend fun getContacts(): Response<List<Contact>>
}