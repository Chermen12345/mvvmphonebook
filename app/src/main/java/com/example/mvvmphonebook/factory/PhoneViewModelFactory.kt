package com.example.mvvmphonebook.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmphonebook.repository.PhoneRepository
import com.example.mvvmphonebook.viewmodel.PhoneViewModel

class PhoneViewModelFactory(val application: Application, val repository: PhoneRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhoneViewModel(application, repository) as T
    }
}