package com.example.deliveryfoodapp.utils

import com.example.deliveryfoodapp.models.CuisineType
import com.example.deliveryfoodapp.models.Wilaya

class OrderStatuses {
    companion object {

        const val IS_WAITING_STATUS = 0
        const val IS_PREPARED_STATUS = 1
        const val PICKED_UP_STATUS = 2
        const val ON_WAY_STATUS = 3
        const val DELIVERED_STATUS = 4
        const val CANCELED_STATUS = 5

        private const val IS_WAITING_VALUE = "Waiting"
        private const val IS_PREPARED_VALUE = "Prepared"
        private const val PICKED_UP_VALUE = "Picked Up"
        private const val ON_WAY_VALUE = "On Way"
        private const val DELIVERED_VALUE = "Delivered"
        private const val CANCELED_VALUE = "Canceled"

        val STATUS_VALUE_LIST = listOf(
            IS_WAITING_VALUE,
            IS_PREPARED_VALUE,
            PICKED_UP_VALUE,
            ON_WAY_VALUE,
            DELIVERED_VALUE,
            CANCELED_VALUE
        )

    }
}

class CuisineTypes {
    companion object {

        private val ALL_CUISINE_TYPES = listOf(
            CuisineType(id = 1,name = "Traditional")
        )

        fun getCuisineTypeByCuisineTypeID(cuisineTypeID : Int) : CuisineType {
            return  ALL_CUISINE_TYPES.find { it.id == cuisineTypeID } ?: CuisineType.emptyCuisineType()
        }
    }
}
class Wilayas {
    companion object {

        private val ALL_WILAYAS = listOf(
            Wilaya(id = 1, name = "Adrar")
        )

        fun getWilayaByWilayaID(wilayaID : Int) : Wilaya {
            return  ALL_WILAYAS.find { it.id == wilayaID } ?: Wilaya.emptyWilaya()
        }
    }
}