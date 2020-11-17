package com.pgricha.neonapp.ui.contacts

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgricha.neonapp.data.Services
import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.util.getDecimalFormat
import com.pgricha.neonapp.util.moneyStringToDouble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ContactsViewModel : ViewModel() {

    private val services = Services()
    val contacts = MutableLiveData<List<Contact>>()
    var transferContacts = ArrayList<Contact>()
    val loadingVisibility = ObservableBoolean(false)

    fun fetchGetContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = services.getContacts()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    contacts.value = response.body()
                    loadingVisibility.set(false)
                }
            }
        }
    }

    fun sendMoney(value: String, contact: Contact) {
        var mValue = value
        if(mValue.equals("")) mValue = ""
        val doubleValue: Double = moneyStringToDouble(mValue)

        if (doubleValue > 0) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            contact.value = "R$ " + getDecimalFormat()?.format(doubleValue)
            contact.date = currentDate
        }
    }
}