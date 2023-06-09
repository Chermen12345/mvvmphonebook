package com.example.mvvmphonebook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmphonebook.db.PhoneEntity
import com.example.mvvmphonebook.repository.PhoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application, private val repository: PhoneRepository): AndroidViewModel(application) {
    fun insertPhone(phone: PhoneEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPhone(phone)
    }
    fun updatePhone(phone: PhoneEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updatePhone(phone)
    }
    fun deletePhone(phone: PhoneEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.deletePhone(phone)
    }
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }
    fun getAllPhones(): LiveData<List<PhoneEntity>> = repository.getAllPhones()
    fun searchPhone(query: String?): LiveData<List<PhoneEntity>> = repository.searchPhone(query)
}