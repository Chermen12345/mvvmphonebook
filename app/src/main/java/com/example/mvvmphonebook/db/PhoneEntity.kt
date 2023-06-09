package com.example.mvvmphonebook.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "phone_table")
@Parcelize
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ?= null,
    val name: String,
    val lastname: String,
    val phone: String

): Parcelable
