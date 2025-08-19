package org.viniciuspinatti;

import java.time.LocalDate;
import java.util.Arrays;
import org.viniciuspinatti.model.Car;
import org.viniciuspinatti.utils.GenericExamples;

public class Main {
  public static void main(String[] args) {
    Car car = Car.createCar("GM", "Onix", LocalDate.parse("2014-01-01"), 180);
    car.executeCarInfoCallable();
    car.executeCarInfoSupplier();

    GenericExamples.needExplicitCastExample();

    String[] words = {"Foo", "Bar"};

    System.out.println("Generic array to List: " + GenericExamples.fromArrayToList(words));
    System.out.println(
        "Multiple generic params array to List: "
            + GenericExamples.fromArrayToList(words, String::length));

    Integer[] intArray = {1, 2, 3};
    System.out.println(
        "Upper-bounded type: " + GenericExamples.upperBoundedFromArrayToList(intArray));

    System.out.println(
        "Wildcard upper example: "
            + GenericExamples.upperBoundedWildCardExample(Arrays.stream(intArray).toList()));

    Number[][] numberArrayMatrix = {{55}, {66, 77}};
    System.out.println(
        "Wildcard lower example 1: "
            + GenericExamples.lowerBoundedWildCardExample(
                Arrays.stream(numberArrayMatrix[0]).toList()));
    System.out.println(
        "Wildcard lower example 2: "
            + GenericExamples.lowerBoundedWildCardExample(
                Arrays.stream(numberArrayMatrix[1]).toList()));
  }
}
