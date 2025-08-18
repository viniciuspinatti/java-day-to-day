package org.viniciuspinatti;

import java.time.LocalDate;
import org.viniciuspinatti.model.Car;

public class Main {
  public static void main(String[] args) {
    Car car = Car.createCar("GM", "Onix", LocalDate.parse("2014-01-01"), 180);
    car.executeCarInfoCallable();
    car.executeCarInfoSupplier();
  }
}
