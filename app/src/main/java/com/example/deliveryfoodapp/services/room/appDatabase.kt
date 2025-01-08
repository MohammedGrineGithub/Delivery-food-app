package com.example.deliveryfoodapp.services.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserCart::class, OrderItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userCartDao(): UserCartDao
    abstract fun orderItemDao(): OrderItemDao

    @SuppressLint("StaticFieldLeak")
    companion object {
        private const val DATABASE_NAME = "user_db"
        private var INSTANCE: AppDatabase? = null
        lateinit var context: Context
        fun buildDatabase(): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,
                    DATABASE_NAME).allowMainThreadQueries().build() }
            return INSTANCE
        }
    }
}