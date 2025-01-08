package com.example.deliveryfoodapp.services.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userCarts")
data class UserCart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val restaurantID: Int
)