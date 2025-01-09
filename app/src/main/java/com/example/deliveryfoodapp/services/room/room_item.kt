package com.example.deliveryfoodapp.services.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class RoomItem(
    @PrimaryKey(autoGenerate = false)
    var itemID: Int,
    var name: String,
    var ingredients: String,
    var price: Double,
    var photo: String
)