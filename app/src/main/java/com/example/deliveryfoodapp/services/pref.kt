package com.example.deliveryfoodapp.services

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit

@SuppressLint("StaticFieldLeak")
object Pref {

    lateinit var context: Context

    /** ********************** User ID ********************** **/

    fun saveUserID(userID:Int) {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        pref.edit {
            putInt("userID",userID)
        }
    }

    fun getUserID():Int {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        val userID = pref.getInt("userID",-1)
        return userID
    }

    fun clearUserID() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("userID")
        }
    }

    /** ********************** User Authentication Token ********************** **/

    fun saveUserToken(userToken:String) {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        pref.edit {
            putString("userToken",userToken)
        }
    }

    fun getUserToken():String? {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        val userToken = pref.getString("userToken","")
        return userToken
    }

    fun clearUserToken() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("userToken")
        }
    }

    /** ******************************** First time ********************************* **/

    // Check if it's the first time the user is entering the app
    fun isFirstTime(): Boolean {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return pref.getBoolean("isFirstTime", true)
    }

    // Set that the user has entered the app for the first time
    fun setFirstTime(isFirst: Boolean) {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            putBoolean("isFirstTime", isFirst)
        }
    }
}