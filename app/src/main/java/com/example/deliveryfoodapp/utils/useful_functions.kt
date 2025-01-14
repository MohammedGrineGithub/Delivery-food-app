package com.example.deliveryfoodapp.utils

import java.time.LocalTime

class DateTimeManipulation {
    companion object {
        fun formatDateStringIntoDate(datetime : String) : String{
            val parts = datetime.split(" ")
            return parts[0]
        }
        fun formatDateStringIntoTime(datetime : String) : String{
            val parts = datetime.split(" ")
            return parts[1]
        }

        fun isWithinOperatingHours(openingTime: LocalTime, closingTime: LocalTime): Boolean {
            val currentTime = LocalTime.now()
            return if (closingTime.isAfter(openingTime) || closingTime == openingTime) {
                currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)
            } else {
                currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)
            }
        }
    }
}