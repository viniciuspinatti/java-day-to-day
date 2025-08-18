package org.viniciuspinatti.model;

import java.time.LocalDate;
import java.util.concurrent.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.viniciuspinatti.service.CalculateReleaseTimeServiceCallable;
import org.viniciuspinatti.service.CheckIfIsVeryFastServiceCallable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Car {
  private String brand;
  private String model;
  private LocalDate releaseDate;
  private Integer maxSpeedKmh;

  public static Car createCar(
      String brand, String model, LocalDate releaseDate, Integer maxSpeedKmh) {
    return new Car(brand, model, releaseDate, maxSpeedKmh);
  }

  public String getCarFullName() {
    return this.brand + " " + this.model;
  }

  // Execution of one async task
  public void executeCarInfoCallable() {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // Each task in the chain follows the pattern “submit-get”. In a long chain, this produces
    // verbose code.
    // When the chain is tolerant to a task failure, we should create a dedicated try/catch block.
    // When invoked, the get() method waits until the Callable returns a value. So the total
    // execution time of the chain equals the sum of the execution time of all the tasks. But if the
    // next task depends on the correct execution of only one previous task, the chain process is
    // significantly slowed down.
    try {
      Future<String> releaseTimeMessageFuture =
          executorService.submit(new CalculateReleaseTimeServiceCallable(this));
      System.out.println(releaseTimeMessageFuture.get());
      Future<Boolean> isVeryFast =
          executorService.submit(new CheckIfIsVeryFastServiceCallable(this));
      System.out.println(getCarFullName() + " Is very fast? " + isVeryFast.get());
      executorService.close();
    } catch (ExecutionException | InterruptedException e) {
      // InterruptedException: thrown when an interruption occurred while the thread is sleeping,
      // active, or occupied
      // ExecutionException: thrown when a task is aborted by throwing an exception. In other words,
      // it’s a wrapper exception, and the real exception that aborted the task is the cause (and it
      // can be inspected using the getCause() method).
      throw new RuntimeException(e.getCause());
    }
  }

  // Supplier is a functional interface whose SAM (Single Abstract Method) is get().
  // One of the most frequent use cases of this interface is to defer the execution of some code.
  // We can also use it in an asynchronous computation context, specifically in the
  // CompletableFuture API.
  // Some methods accept a Supplier as a parameter, such as the supplyAsync() method.
  public void executeCarInfoSupplier() {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // In this case, a lambda expression defines the Supplier, but we may also define an
    // implementation class. Thanks to the CompletableFuture, we have defined a template for the
    // asynchronous operation, making it simpler to understand and easier to modify.
    // The join() method provides the return value of the Supplier.
    CompletableFuture<String> releaseTimeMessageFuture =
        CompletableFuture.supplyAsync(
            () -> new CalculateReleaseTimeServiceCallable(this).call(), executorService);
    CompletableFuture<Boolean> isVeryFast =
        CompletableFuture.supplyAsync(
                () -> new CheckIfIsVeryFastServiceCallable(this).call(), executorService)
            .exceptionally((ex) -> false);

    // Defining a chain of asynchronous tasks with the CompletableFuture–Supplier approach may solve
    // some problems introduced before with the Future–Callable approach:
    // - Each task of the chain is isolated. So if the execution of a task fails, we can handle it
    // via the exceptionally() block.
    // - join() method doesn’t need to handle checked exceptions at compile time.
    // - We can design an asynchronous task template, improving the status handling of each task.
    System.out.println(releaseTimeMessageFuture.join());
    System.out.println(getCarFullName() + " Is very fast? " + isVeryFast.join());
    executorService.close();
  }
}
