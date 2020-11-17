package com.pgricha.neonapp.model

import com.google.gson.annotations.SerializedName

data class Contacts (
    @SerializedName("contacts")
    val profiles: List<Contact>?
)