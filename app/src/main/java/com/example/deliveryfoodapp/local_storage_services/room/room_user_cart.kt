package com.example.deliveryfoodapp.local_storage_services.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userCarts")
data class RoomUserCart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val restaurantID: Int
)