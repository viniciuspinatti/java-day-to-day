package org.viniciuspinatti.service;

import java.util.concurrent.Callable;
import org.viniciuspinatti.model.Car;

public class CheckIfIsVeryFastServiceCallable implements Callable<Boolean> {
  private final Car car;

  public CheckIfIsVeryFastServiceCallable(Car car) {
    this.car = car;
  }

  @Override
  public Boolean call() {
    return this.car.getMaxSpeedKmh() > 200;
  }
}
