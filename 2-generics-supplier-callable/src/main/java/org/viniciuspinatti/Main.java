package org.viniciuspinatti;

import org.viniciuspinatti.model.Car;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Car car = Car.createCar("GM", "Onix", LocalDate.parse("2014-01-01"));
        car.execute();
    }
}