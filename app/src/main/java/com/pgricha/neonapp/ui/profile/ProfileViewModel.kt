package com.pgricha.neonapp.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgricha.neonapp.data.Services
import com.pgricha.neonapp.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {

    private val services = Services()

    val token = MutableLiveData<Profile>()

    fun fetchGetProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = services.getProfile()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    token.value = response.body()
                }
            }
        }
    }
}