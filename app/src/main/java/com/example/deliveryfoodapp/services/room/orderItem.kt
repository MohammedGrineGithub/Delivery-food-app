package com.example.deliveryfoodapp.services.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orderItems",
    foreignKeys = [
        ForeignKey(
            entity = UserCart::class,
            parentColumns = ["id"],
            childColumns = ["userCartId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val itemId: Int,
    val note: String?,
    val itemQuantity: Int,
    val userCartId : Int
)