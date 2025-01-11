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

        val ALL_CUISINE_TYPES = listOf(
            CuisineType(0, "None") ,
            CuisineType(1, "Algerian"),
            CuisineType(2, "Italian"),
            CuisineType(3, "Chinese"),
            CuisineType(4, "French"),
            CuisineType(5, "Japanese"),
            CuisineType(6, "Indian"),
            CuisineType(7, "Mexican"),
            CuisineType(8, "Thai"),
            CuisineType(9, "Spanish"),
            CuisineType(10, "Greek")
        )

        fun getCuisineTypeByCuisineTypeID(cuisineTypeID : Int) : CuisineType {
            return  ALL_CUISINE_TYPES.find { it.id == cuisineTypeID } ?: CuisineType.emptyCuisineType()
        }
    }
}
class Wilayas {
    companion object {

        val ALL_WILAYAS = listOf(
            Wilaya(0, "None"),
            Wilaya(1, "Adrar"),
            Wilaya(2, "Chlef"),
            Wilaya(3, "Laghouat"),
            Wilaya(4, "Oum El Bouaghi"),
            Wilaya(5, "Batna"),
            Wilaya(6, "Béjaïa"),
            Wilaya(7, "Biskra"),
            Wilaya(8, "Béchar"),
            Wilaya(9, "Blida"),
            Wilaya(10, "Bouira"),
            Wilaya(11, "Tamanrasset"),
            Wilaya(12, "Tébessa"),
            Wilaya(13, "Tlemcen"),
            Wilaya(14, "Tiaret"),
            Wilaya(15, "Tizi Ouzou"),
            Wilaya(16, "Algiers"),
            Wilaya(17, "Djelfa"),
            Wilaya(18, "Jijel"),
            Wilaya(19, "Sétif"),
            Wilaya(20, "Saïda"),
            Wilaya(21, "Skikda"),
            Wilaya(22, "Sidi Bel Abbès"),
            Wilaya(23, "Annaba"),
            Wilaya(24, "Guelma"),
            Wilaya(25, "Constantine"),
            Wilaya(26, "Médéa"),
            Wilaya(27, "Mostaganem"),
            Wilaya(28, "M’Sila"),
            Wilaya(29, "Mascara"),
            Wilaya(30, "Ouargla"),
            Wilaya(31, "Oran"),
            Wilaya(32, "El Bayadh"),
            Wilaya(33, "Illizi"),
            Wilaya(34, "Bordj Bou Arreridj"),
            Wilaya(35, "Boumerdès"),
            Wilaya(36, "El Tarf"),
            Wilaya(37, "Tindouf"),
            Wilaya(38, "Tissemsilt"),
            Wilaya(39, "El Oued"),
            Wilaya(40, "Khenchela"),
            Wilaya(41, "Souk Ahras"),
            Wilaya(42, "Tipaza"),
            Wilaya(43, "Mila"),
            Wilaya(44, "Aïn Defla"),
            Wilaya(45, "Naâma"),
            Wilaya(46, "Aïn Témouchent"),
            Wilaya(47, "Ghardaïa"),
            Wilaya(48, "Relizane"),
            Wilaya(49, "Timimoun"),
            Wilaya(50, "Bordj Badji Mokhtar"),
            Wilaya(51, "Ouled Djellal"),
            Wilaya(52, "Béni Abbès"),
            Wilaya(53, "In Salah"),
            Wilaya(54, "In Guezzam"),
            Wilaya(55, "Touggourt"),
            Wilaya(56, "Djanet"),
            Wilaya(57, "El M'Ghair"),
            Wilaya(58, "El Meniaa")
        )

        fun getWilayaByWilayaID(wilayaID : Int) : Wilaya {
            return  ALL_WILAYAS.find { it.id == wilayaID } ?: Wilaya.emptyWilaya()
        }
    }
}