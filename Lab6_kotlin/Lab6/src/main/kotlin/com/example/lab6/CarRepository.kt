package com.example.lab6

import java.io.*
import java.util.stream.Collectors

class CarRepository {
    val carList: MutableList<Car> = mutableListOf()

    init {
        loadCars()
    }

    fun addCar(car: Car) {
        carList.add(car)
        saveCars()
    }

    fun removeCar(car: Car) {
        carList.remove(car)
        saveCars()
    }

    val availableCars: List<Car>
        get() = carList.stream()
                .filter { car: Car -> !car.isRented }
                .collect(Collectors.toList())
    val rentedCars: List<Car>
        get() = carList.stream()
                .filter(Car::isRented)
                .collect(Collectors.toList())

    fun getCarsFreeInMonth(month: Int): List<Car> {
        return carList.stream()
                .filter { car: Car -> car.isRented && car.rentalDate!!.plusMonths(car.rentalPeriod.toLong()).monthValue == month }
                .collect(Collectors.toList())
    }

    fun getFilteredCars(brand: String, licensePlate: String): List<Car> {
        return carList.stream()
                .filter { car: Car -> (brand.isEmpty() || car.brand == brand) && (licensePlate.isEmpty() || car.licensePlate == licensePlate) }
                .collect(Collectors.toList())
    }

    private fun saveCars() {
        try {
            val fos = FileOutputStream(filename)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(carList)
            oos.close()
            fos.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    private fun loadCars() {
        try {
            val fis = FileInputStream(filename)
            val ois = ObjectInputStream(fis)
            val loadedCars = ois.readObject() as ArrayList<Car>
            ois.close()
            fis.close()

            carList.clear()
            carList.addAll(loadedCars)
        } catch (fnfe: FileNotFoundException) {
            println("No previous cars found. Initializing an empty list.")
            carList.clear()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        } catch (c: ClassNotFoundException) {
            println("Class not found")
            c.printStackTrace()
        }
    }

    companion object {
        private const val filename = "cars.h2"
    }
}
