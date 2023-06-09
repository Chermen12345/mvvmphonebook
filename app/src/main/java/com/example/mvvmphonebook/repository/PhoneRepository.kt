package com.example.mvvmphonebook.repository

import androidx.lifecycle.LiveData
import com.example.mvvmphonebook.db.PhoneDao
import com.example.mvvmphonebook.db.PhoneEntity

class PhoneRepository(private val dao: PhoneDao) {
    suspend fun insertPhone(phone: PhoneEntity){
        dao.insertPhone(phone)
    }
    suspend fun updatePhone(phone: PhoneEntity){
        dao.updatePhone(phone)
    }
    suspend fun deletePhone(phone: PhoneEntity){
        dao.deletePhone(phone)
    }
    suspend fun deleteAll() = dao.deleteAll()
    fun getAllPhones(): LiveData<List<PhoneEntity>> = dao.getAllPhones()
    fun searchPhone(query: String?): LiveData<List<PhoneEntity>> = dao.searchPhone(query)
}