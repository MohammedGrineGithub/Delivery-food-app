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

    fun clearID() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("userID")
        }
    }

    /** ********************** User Email/Password authentication Token ********************** **/

    fun saveUserEmailPasswordToken(userEmailPasswordToken:String) {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        pref.edit {
            putString("userEmailPasswordToken",userEmailPasswordToken)
        }
    }

    fun getUserEmailPasswordToken():String? {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        val userEmailPasswordToken = pref.getString("userEmailPasswordToken","")
        return userEmailPasswordToken
    }

    fun clearUserEmailPasswordToken() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("userEmailPasswordToken")
        }
    }

    /** ********************** User Google authentication Token ********************** **/

    fun saveUserGoogleToken(userGoogleToken:String) {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        pref.edit {
            putString("userGoogleToken",userGoogleToken)
        }
    }

    fun getUserGoogleToken():String? {
        val pref = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE)
        val userGoogleToken = pref.getString("userGoogleToken","")
        return userGoogleToken
    }

    fun clearUserGoogleToken() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("userGoogleToken")
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

    fun clearFirstTime() {
        val pref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        pref.edit {
            remove("isFirstTime")
        }
    }

}