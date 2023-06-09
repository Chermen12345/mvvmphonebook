package com.example.mvvmphonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmphonebook.db.PhoneDao
import com.example.mvvmphonebook.db.PhoneDb
import com.example.mvvmphonebook.factory.PhoneViewModelFactory
import com.example.mvvmphonebook.repository.PhoneRepository
import com.example.mvvmphonebook.viewmodel.PhoneViewModel

class MainActivity : AppCompatActivity() {
    lateinit var db: PhoneDb
    lateinit var dao: PhoneDao
    lateinit var repository: PhoneRepository
    lateinit var factory: PhoneViewModelFactory
    lateinit var viewmodel: PhoneViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        db = PhoneDb.getDb(this)
        dao = db.getDao()
        repository = PhoneRepository(dao)
        factory = PhoneViewModelFactory(application, repository)
        viewmodel = ViewModelProvider(this, factory).get(PhoneViewModel::class.java)
    }
}