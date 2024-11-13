package com.example.deliveryfoodapp.services

import android.app.Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Pref.context = applicationContext
    }
}