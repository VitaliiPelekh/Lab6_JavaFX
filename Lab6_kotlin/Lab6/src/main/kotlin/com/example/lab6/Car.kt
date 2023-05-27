package com.example.lab6

import java.io.Serializable
import java.time.LocalDate

class Car(@JvmField val brand: String, @JvmField val licensePlate: String, private val yearOfProduction: Int) : Serializable {
    @JvmField
    var isRented = false
    @JvmField
    var rentalDate: LocalDate? = null
    @JvmField
    var rentalPeriod = 0

    override fun toString(): String {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + yearOfProduction +
                ", isRented=" + isRented +
                ", rentalDate=" + rentalDate +
                ", rentalPeriod=" + rentalPeriod +
                '}'
    }
}