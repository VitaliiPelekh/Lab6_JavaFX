package com.example.lab6

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.TextField

class CarController {
    @FXML
    lateinit var carListView: ListView<Car>

    @FXML
    lateinit var brandField: TextField

    @FXML
    lateinit var licensePlateField: TextField

    @FXML
    lateinit var yearField: TextField

    @FXML
    lateinit var filterBrandField: TextField

    @FXML
    lateinit var filterLicensePlateField: TextField

    @FXML
    lateinit var filterButton: Button

    @FXML
    lateinit var clearFilterButton: Button

    @FXML
    lateinit var clearButton: Button

    private val carRepo: CarRepository = CarRepository()

    @FXML
    fun initialize() {
        carListView.items.addAll(carRepo.carList)
        filterButton.setOnAction { filterCars() }
        clearFilterButton.setOnAction { clearFilter() }
        clearButton.setOnAction { onClearButtonClick() }
    }

    @FXML
    protected fun onAddCarButtonClick() {
        val brand = brandField.text
        val licensePlate = licensePlateField.text
        val yearText = yearField.text
        if (yearText.isEmpty()) {
            // display an error to the user that the year field is empty
            return
        }
        val year = yearText.toIntOrNull() ?: return
        val car = Car(brand, licensePlate, year)
        carRepo.addCar(car)
        carListView.items.add(car)
        brandField.clear()
        licensePlateField.clear()
        yearField.clear()
    }

    @FXML
    protected fun onRemoveCarButtonClick() {
        val selectedCar = carListView.selectionModel.selectedItem
        if (selectedCar != null) {
            carRepo.removeCar(selectedCar)
            carListView.items.remove(selectedCar)
        }
    }

    @FXML
    private fun filterCars() {
        val brand = filterBrandField.text
        val licensePlate = filterLicensePlateField.text
        carListView.items.clear()
        carListView.items.addAll(carRepo.getFilteredCars(brand, licensePlate))
    }

    @FXML
    private fun clearFilter() {
        filterBrandField.clear()
        filterLicensePlateField.clear()
        carListView.items.clear()
        carListView.items.addAll(carRepo.carList)
    }

    @FXML
    protected fun onClearButtonClick() {
        brandField.clear()
        licensePlateField.clear()
        yearField.clear()
        filterBrandField.clear()
        filterLicensePlateField.clear()
    }
}
