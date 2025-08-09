package org.viniciuspinatti.service;

import org.viniciuspinatti.model.Car;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.Callable;

// Callable is an interface introduced in version 5 of Java and evolved as a functional interface in version 8.
public class CalculateReleaseTimeService implements Callable<String> {
    private final Car car;

    // call() is designed to encapsulate a task that should be executed by another thread, such as the Runnable interface.
    // That’s because Callable instances can be executed via ExecutorService.
    // When the call() method returns a value, the main thread retrieves it to do its logic. For this,
    // we can use Future, an object that tracks and obtains the value upon completion of a task executed on another thread.
    @Override
    public String call() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(car.getBrand());
        stringBuilder.append(" Released ");
        stringBuilder.append(Period.between(car.getReleaseDate(), LocalDate.now()).getYears());
        stringBuilder.append(" years ago");
        return stringBuilder.toString();
    }

    public CalculateReleaseTimeService(Car car) {
        this.car = car;
    }
}
