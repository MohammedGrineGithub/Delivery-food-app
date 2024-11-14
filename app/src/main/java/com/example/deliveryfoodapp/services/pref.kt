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

}