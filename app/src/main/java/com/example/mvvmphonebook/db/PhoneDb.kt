package com.example.mvvmphonebook.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvmphonebook.db.PhoneDb

@Database(entities = [PhoneEntity::class], version = 1, exportSchema = true)

abstract class PhoneDb: RoomDatabase() {
    abstract fun getDao(): PhoneDao


    companion object{
        fun getDb(context: Context): PhoneDb{
            val instance = Room.databaseBuilder(context, PhoneDb::class.java, "phone.db").build()
            return instance
        }
    }

    }
