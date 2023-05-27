package com.example.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    private List<Car> carList;
    private static final String filename = "cars.h2";

    public CarRepository() {
        this.carList = new ArrayList<>();
        loadCars();
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void addCar(Car car) {
        carList.add(car);
        saveCars();
    }

    public void removeCar(Car car) {
        carList.remove(car);
        saveCars();
    }

    public List<Car> getAvailableCars() {
        return carList.stream()
                .filter(car -> !car.getIsRented())
                .collect(Collectors.toList());
    }

    public List<Car> getRentedCars() {
        return carList.stream()
                .filter(Car::getIsRented)
                .collect(Collectors.toList());
    }

    public List<Car> getCarsFreeInMonth(int month) {
        return carList.stream()
                .filter(car -> car.getIsRented() && car.getRentalDate().plusMonths(car.getRentalPeriod()).getMonthValue() == month)
                .collect(Collectors.toList());
    }

    public List<Car> getFilteredCars(String brand, String licensePlate) {
        return carList.stream()
                .filter(car -> (brand.isEmpty() || car.getBrand().equals(brand)) && (licensePlate.isEmpty() || car.getLicensePlate().equals(licensePlate)))
                .collect(Collectors.toList());
    }


    private void saveCars() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(carList);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void loadCars() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            carList = (ArrayList<Car>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("No previous cars found. Initializing an empty list.");
            carList = new ArrayList<>();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
}
