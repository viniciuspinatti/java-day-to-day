package org.viniciuspinatti;

import java.util.List;

public class Utils {
  public static void measureExecutionTime(String operation, Runnable method) {
    long startTime = System.nanoTime();

    method.run();

    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;

    System.out.println(operation + ": " + timeElapsed / 1_000_000 + " ms");
  }

  public static void measureExecutionTimeNano(String operation, Runnable method) {
    long startTime = System.nanoTime();

    method.run();

    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;

    System.out.println(operation + ": " + timeElapsed + " nano");
  }

  public static void insertAtBeginning(List<String> list, int size) {
    for (int i = 0; i < size; i++) {
      list.add(0, "Element " + i);
    }
  }

  public static void insertAtMiddle(List<String> list, int size) {
    list.add(size / 2, "Middle inserted");
  }

  public static void insertAtEnd(List<String> list, int size) {
    for (int i = 0; i < size; i++) {
      list.add("Element " + i);
    }
  }

  public static void accessElements(List<String> list) {
    for (int i = 0; i < list.size(); i++) {
      list.get(i);
    }
  }
}
