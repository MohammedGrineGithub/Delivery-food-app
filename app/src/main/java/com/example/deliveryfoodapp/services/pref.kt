package com.example.deliveryfoodapp.services

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit

@SuppressLint("StaticFieldLeak")
object Pref {

    lateinit var context: Context

    fun saveUserID(userID:String) {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        pref.edit {
            putString("UserID",userID)
        }
    }

    fun getID():String? {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        val userID = pref.getString("UserID","")
        return userID
    }

    fun clearID() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("UserID") // Removes the "ID" entry
        }
    }

    // Check if it's the first time the user is entering the app
    fun isFirstTime(): Boolean {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return pref.getBoolean("isFirstTime", true)
    }
    ///
    // Set that the user has entered the app for the first time
    fun setFirstTime(isFirst: Boolean) {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            putBoolean("isFirstTime", isFirst)
        }
    }

}