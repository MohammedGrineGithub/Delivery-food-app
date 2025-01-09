package com.example.deliveryfoodapp.services.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orderItems",
    foreignKeys = [
        ForeignKey(
            entity = RoomUserCart::class,
            parentColumns = ["id"],
            childColumns = ["userCartId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        /* ForeignKey(
            entity = RoomItem::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )*/
    ]
)
data class RoomOrderItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded
    val item: RoomItem,
    var note: String?,
    var itemQuantity: Int,
    val userCartId : Int
)