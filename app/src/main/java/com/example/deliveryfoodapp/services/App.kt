package com.example.deliveryfoodapp.services

import android.app.Application
import com.example.deliveryfoodapp.services.room.AppDatabase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Pref.context = applicationContext
        AppDatabase.context = applicationContext
    }
}