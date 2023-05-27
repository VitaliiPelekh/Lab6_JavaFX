package com.example.lab6;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {
    private String brand;
    private String licensePlate;
    private int yearOfProduction;
    private boolean isRented;
    private LocalDate rentalDate;
    private int rentalPeriod;

    public Car(String brand, String licensePlate, int yearOfProduction) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.yearOfProduction = yearOfProduction;
        this.isRented = false;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(boolean rented) {
        isRented = rented;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + yearOfProduction +
                ", isRented=" + isRented +
                ", rentalDate=" + rentalDate +
                ", rentalPeriod=" + rentalPeriod +
                '}';
    }
}
