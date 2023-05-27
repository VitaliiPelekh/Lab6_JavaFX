package com.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class CarController {
    @FXML
    private ListView<Car> carListView;
    @FXML
    private TextField brandField;
    @FXML
    private TextField licensePlateField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField filterBrandField;
    @FXML
    private TextField filterLicensePlateField;
    @FXML
    private Button filterButton;
    @FXML
    private Button clearFilterButton;
    @FXML
    private Button clearButton;

    private CarRepository carRepo;

    public CarController() {
        carRepo = new CarRepository();
    }

    @FXML
    public void initialize() {
        carListView.getItems().addAll(carRepo.getCarList());
        filterButton.setOnAction(event -> filterCars());
        clearFilterButton.setOnAction(event -> clearFilter());
        clearButton.setOnAction(event -> onClearButtonClick());
    }

    @FXML
    protected void onAddCarButtonClick() {
        String brand = brandField.getText();
        String licensePlate = licensePlateField.getText();

        String yearText = yearField.getText();
        if (yearText.isEmpty()) {
            // display an error to the user that the year field is empty
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            // display an error to the user that the year field does not contain a valid number
            return;
        }

        Car car = new Car(brand, licensePlate, year);
        carRepo.addCar(car);
        carListView.getItems().add(car);
        brandField.clear();
        licensePlateField.clear();
        yearField.clear();
    }


    @FXML
    protected void onRemoveCarButtonClick() {
        Car selectedCar = carListView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            carRepo.removeCar(selectedCar);
            carListView.getItems().remove(selectedCar);
        }
    }

    @FXML
    private void filterCars() {
        String brand = filterBrandField.getText();
        String licensePlate = filterLicensePlateField.getText();
        carListView.getItems().clear();
        carListView.getItems().addAll(carRepo.getFilteredCars(brand, licensePlate));
    }

    @FXML
    private void clearFilter() {
        filterBrandField.clear();
        filterLicensePlateField.clear();
        carListView.getItems().clear();
        carListView.getItems().addAll(carRepo.getCarList());
    }

    @FXML
    protected void onClearButtonClick() {
        brandField.clear();
        licensePlateField.clear();
        yearField.clear();
        filterBrandField.clear();
        filterLicensePlateField.clear();
    }
}
