package com.example.mvvmphonebook.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: PhoneEntity)
    @Update
    suspend fun updatePhone(phone: PhoneEntity)
    @Delete
    suspend fun deletePhone(phone: PhoneEntity)
    @Query("SELECT * FROM phone_table  ORDER BY id DESC")
    fun getAllPhones(): LiveData<List<PhoneEntity>>
    @Query("DELETE FROM phone_table")
    suspend fun deleteAll()
    @Query("SELECT * FROM phone_table WHERE name LIKE :query OR lastname LIKE :query OR phone LIKE :query")
    fun searchPhone(query: String?): LiveData<List<PhoneEntity>>
}