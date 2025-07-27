package org.viniciuspinatti;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    handleImmutableList();

    // In terms of performance, HashMap is slightly faster than LinkedHashMap because it doesn’t
    // need to maintain the insertion order. Most operations, like adding, removing, and looking up
    // elements, take constant time, O(1).
    // LinkedHashMap performs similarly for these operations but has a slight overhead due to the
    // linked list that maintains the order.
    handleHashMap();

    handleLinkedHashMap();

    handleSet();

    handleArrayList();

    handleLinkedList();
  }

  private static void runningMessage(String methodName) {
    System.out.println();
    System.out.println("### RUNNING " + methodName + " ###");
  }

  private static void handleImmutableList() {
    runningMessage("handleImmutableList");
    // The List interface is dedicated to storing ordered collections of objects.
    // It allows us to positionally access and inserts new elements, as well as save duplicate
    // values
    // List.of will create an immutable collection
    List<String> immutableNamesList = List.of("Vinícius", "Xavier", "Pinatti");

    for (String name : immutableNamesList) {
      System.out.println("Names in immutableNamesList -> " + name);
    }

    try {
      immutableNamesList.add("ABC");
    } catch (UnsupportedOperationException e) {
      System.out.println("Error trying to modify an immutable list");
    }
  }

  private static void handleHashMap() {
    runningMessage("handleHashMap");
    // The Map interface supports a key-value pair mapping of the data. To access a certain value,
    // we need to know its unique key.
    // A common antipattern we sometimes encounter is trying to maintain order using a map.
    // Thus, not making use of other collection types more suitable for the job.
    // Maps are designed for quick access and search based on unique keys.
    // When we want to maintain order or work with position-based indexes, lists are a natural
    // choice.
    Map<String, String> namesMap = new HashMap<>();
    namesMap.put("01", "A");
    namesMap.put("3", "B");
    namesMap.put("004", "C");
    namesMap.put("5", "D");
    namesMap.put("02", "E");

    for (String name : namesMap.values()) {
      System.out.println("Current value (namesMap) is -> " + name);
    }

    for (Map.Entry<String, String> entry : namesMap.entrySet()) {
      System.out.println(
          "namesMap: Current key -> " + entry.getKey() + ", current value -> " + entry.getValue());
    }
  }

  private static void handleLinkedHashMap() {
    runningMessage("handleLinkedHashMap");
    // Unlike HashMap, which does not guarantee any order of elements, LinkedHashMap preserves
    // the order in which key-value pairs are inserted. When iterating over a LinkedHashMap,
    // the elements are returned in the same sequence they were added.
    LinkedHashMap<String, String> namesLinkedHashMap = new LinkedHashMap<>();
    namesLinkedHashMap.put("01", "A");
    namesLinkedHashMap.put("3", "B");
    namesLinkedHashMap.put("004", "C");
    namesLinkedHashMap.put("5", "D");
    namesLinkedHashMap.put("02", "E");

    for (String name : namesLinkedHashMap.values()) {
      System.out.println("Current value (namesLinkedHashMap) is -> " + name);
    }

    for (Map.Entry<String, String> entry : namesLinkedHashMap.entrySet()) {
      System.out.println(
          "namesLinkedHashMap: Current key -> "
              + entry.getKey()
              + ", current value -> "
              + entry.getValue());
    }
  }

  private static void handleSet() {
    runningMessage("handleSet");
    // Main features of the Set
    // Do not consider duplicated values (so you can have just one null element)
    // Not a good option if the order of the elements is important (but there is TreeSet)
    // Can't get elements by index
    // It supports Generics and this is a good idea to avoid ClassCastException

    Set<String> namesSet = new HashSet<>();
    namesSet.add("Vinicius");
    namesSet.add("Xavier");
    namesSet.add("Vinicius");
    namesSet.add("Pinatti");

    for (String name : namesSet) {
      System.out.println("Iterate namesArrayList -> " + name);
    }

    // Create a set from a list will remove duplicates
    List<String> arrayList = new ArrayList<>();
    arrayList.add("Foo");
    arrayList.add("Bar");
    arrayList.add("Baz");
    arrayList.add("Foo");

    Set<String> setNoDup = new HashSet<>(arrayList);
    System.out.println("Original list " + arrayList);
    System.out.println("Set created from list " + setNoDup);
  }

  private static void handleArrayList() {
    runningMessage("handleArrayList");
    // Uses a dynamic array, where elements are stored in contiguous memory locations.
    // Generally more memory-efficient for storing elements, as it doesn't require storing
    // additional
    // pointers for each element.
    // Very fast (O(1)) because it can directly access elements by their index. (get)
    // Insertion/Deletion (add/remove): Can be slow (O(n)) for operations in the middle of the list
    // because it may require shifting elements.
    // Best for scenarios where you need to frequently access elements by index and have fewer
    // insertions/deletions in the middle of the list.
    ArrayList<String> namesArrayList = new ArrayList<>();
    namesArrayList.add("Vinicius");
    namesArrayList.add("Xavier");
    namesArrayList.add("Pinatti");

    for (String name : namesArrayList) {
      System.out.println("Iterate namesArrayList -> " + name);
    }

    // However, it can waste some memory if the internal array is larger than the number of
    // elements.
    ArrayList<String> namesArrayListFixed = new ArrayList<>(5);
    namesArrayListFixed.add("Foo");
    System.out.println("namesArrayListFixed size -> " + namesArrayListFixed.size());
    printInternalArrayLength(namesArrayListFixed);

    List<String> arrayListOperations = new ArrayList<>();
    int listSize = 100000;

    List<String> arrayListFilled = new LinkedList<>();

    for (int i = 0; i < listSize; i++) {
      arrayListFilled.add("Element " + i);
    }

    Utils.measureExecutionTime(
        "ArrayList Insert at Beginning",
        () -> Utils.insertAtBeginning(arrayListOperations, listSize));

    Utils.measureExecutionTimeNano(
        "ArrayList Insert at Middle", () -> Utils.insertAtMiddle(arrayListFilled, listSize));

    Utils.measureExecutionTime(
        "ArrayList Insert at End", () -> Utils.insertAtEnd(arrayListOperations, listSize));

    Utils.measureExecutionTime(
        "ArrayList Access Elements", () -> Utils.accessElements(arrayListFilled));
  }

  private static void printInternalArrayLength(ArrayList<?> list) {
    /**
     * Print the actual length of the internal array of an ArrayList using reflection. WARNING:
     * Using reflection to access internal fields is generally not recommended for production code
     * as it breaks encapsulation and can lead to issues if the internal implementation changes.
     */
    try {
      Field elementDataField = ArrayList.class.getDeclaredField("elementData");
      elementDataField.setAccessible(true); // Allow access to private field
      Object[] elementData = (Object[]) elementDataField.get(list);
      System.out.println("Internal array length: " + elementData.length);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      System.err.println("Error accessing internal array: " + e.getMessage());
    }
  }

  private static void handleLinkedList() {
    runningMessage("handleLinkedList");
    // Uses a doubly linked list, where each element (node) stores a reference to the previous and
    // next element.
    // Less memory-efficient than ArrayList because it stores extra pointers for each element.
    // Relatively slow (O(n)) because it needs to traverse the list from the beginning or end to
    // reach a specific index. (get)
    // Insertion/Deletion (add/remove): Very fast (O(1)) at any position because it only requires
    // updating pointers.
    // Best for scenarios where you need to frequently insert or delete elements, especially at the
    // beginning or end of the list.
    LinkedList<String> namesLinkedList = new LinkedList<>();
    namesLinkedList.add("Vinicius");
    namesLinkedList.add("Xavier");
    namesLinkedList.add("Pinatti");

    for (String name : namesLinkedList) {
      System.out.println("Iterate namesLinkedList -> " + name);
    }

    List<String> linkedListOperations = new LinkedList<>();
    int listSize = 100000;

    List<String> linkedListFilled = new LinkedList<>();

    for (int i = 0; i < listSize; i++) {
      linkedListFilled.add("Element " + i);
    }

    Utils.measureExecutionTime(
        "LinkedList Insert at Beginning",
        () -> Utils.insertAtBeginning(linkedListOperations, listSize));

    // Even with better performance for inserting at the beginning and end of the list, inserting
    // items at an index in the middle of the array does not demonstrate better performance than
    // ArrayList
    Utils.measureExecutionTimeNano(
        "LinkedList Insert at Middle", () -> Utils.insertAtMiddle(linkedListFilled, listSize));

    Utils.measureExecutionTime(
        "LinkedList Insert at End", () -> Utils.insertAtEnd(linkedListOperations, listSize));

    Utils.measureExecutionTime(
        "LinkedList Access Elements", () -> Utils.accessElements(linkedListFilled));
  }
}
