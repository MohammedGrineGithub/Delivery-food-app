package com.example.deliveryfoodapp.local_storage_services

import android.app.Application
import com.example.deliveryfoodapp.local_storage_services.room.AppDatabase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Pref.context = applicationContext
        AppDatabase.context = applicationContext
    }
}