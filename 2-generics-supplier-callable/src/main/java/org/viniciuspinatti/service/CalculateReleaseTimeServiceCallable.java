package org.viniciuspinatti.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.Callable;
import org.viniciuspinatti.model.Car;

// Callable is an interface introduced in version 5 of Java and evolved as a functional interface in
// version 8.
public class CalculateReleaseTimeServiceCallable implements Callable<String> {
  private final Car car;

  public CalculateReleaseTimeServiceCallable(Car car) {
    this.car = car;
  }

  // call() is designed to encapsulate a task that should be executed by another thread, such as the
  // Runnable interface.
  // That’s because Callable instances can be executed via ExecutorService.
  // When the call() method returns a value, the main thread retrieves it to do its logic. For this,
  // we can use Future, an object that tracks and obtains the value upon completion of a task
  // executed on another thread.
  @Override
  public String call() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(car.getCarFullName());
    stringBuilder.append(" Released ");
    stringBuilder.append(Period.between(car.getReleaseDate(), LocalDate.now()).getYears());
    stringBuilder.append(" years ago");
    return stringBuilder.toString();
  }
}
