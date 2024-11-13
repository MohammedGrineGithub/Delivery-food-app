package com.example.deliveryfoodapp.services

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit

@SuppressLint("StaticFieldLeak")
object Pref {

    lateinit var context: Context

    fun saveID(id:String) {
        val pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
        pref.edit {
            putString("ID",id)
        }
    }

    fun getID():String? {
        val pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
        val id = pref.getString("ID","")
        return id
    }

    fun clearID() {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("ID") // Removes the "ID" entry
        }
    }

}