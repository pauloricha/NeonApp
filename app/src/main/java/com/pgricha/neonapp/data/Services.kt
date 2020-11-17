package com.pgricha.neonapp.data

import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.model.Profile
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Services {
    private val api: Api = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/pauloricha/neonapptest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    suspend fun getProfile(): Response<Profile> =
        api.getProfile()

    suspend fun getContacts(): Response<List<Contact>> =
        api.getContacts()
}