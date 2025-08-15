package org.viniciuspinatti.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.viniciuspinatti.service.CalculateReleaseTimeServiceCallable;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Car {
    private String brand;
    private String model;
    private LocalDate releaseDate;

    public static Car createCar(String brand, String model, LocalDate releaseDate) {
        return new Car(brand, model, releaseDate);
    }

    // Execution of one async task
    public Car execute() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Future<String> releaseTimeMessageFuture = executorService.submit(new CalculateReleaseTimeServiceCallable(this));
            System.out.println(releaseTimeMessageFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            // InterruptedException: thrown when an interruption occurred while the thread is sleeping, active, or occupied
            // ExecutionException: thrown when a task is aborted by throwing an exception. In other words, it’s a wrapper exception, and the real exception that aborted the task is the cause (and it can be inspected using the getCause() method).
            throw new RuntimeException(e.getCause());
        }
        return this;
    }
}
