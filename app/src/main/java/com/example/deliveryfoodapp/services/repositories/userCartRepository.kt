package com.example.deliveryfoodapp.services.repositories

import com.example.deliveryfoodapp.services.room.AppDatabase
import com.example.deliveryfoodapp.services.room.UserCart

object UserCartRepository {

    // Add a UserCart
    suspend fun addUserCart(userCart: UserCart) {
        AppDatabase.buildDatabase()?.userCartDao()?.insertUserCart(userCart)
    }

    // Get all UserCarts
    suspend fun getAllUserCarts() =
        AppDatabase.buildDatabase()?.userCartDao()?.getAllUserCarts()

    // Get a UserCart by its ID
    suspend fun getUserCartById(userCartId: Int) =
        AppDatabase.buildDatabase()?.userCartDao()?.getUserCartById(userCartId)

    // Remove a UserCart by its ID
    suspend fun removeUserCart(userCart: UserCart) {
        AppDatabase.buildDatabase()?.userCartDao()?.deleteUserCart(userCart)
    }
}