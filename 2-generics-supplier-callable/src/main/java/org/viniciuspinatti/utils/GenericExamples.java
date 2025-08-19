package org.viniciuspinatti.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

// Oracle recommendation is to use an uppercase letter to represent a generic type and to choose a
// more descriptive letter to represent formal types.
// In Java Collections, we use T for type, K for key and V for value.
public class GenericExamples {
  // The compiler will complain about the last line. It doesn’t know what data type is returned.
  public static void needExplicitCastExample() {
    List list = new LinkedList();
    list.add(Integer.valueOf("1"));
    // No contract could guarantee that the return type of the list is an Integer. The defined list
    // could hold any object.
    // When looking at types, it can only guarantee that it is an Object and therefore requires an
    // explicit cast to ensure that the type is safe.
    Integer i = (Integer) list.iterator().next();
    System.out.println("Cast to " + i.getClass());
  }

  // These are some properties of generic methods:
  // - Generic methods have a type parameter (the diamond operator enclosing the type) before the
  // return type of the method declaration.
  // - Type parameters can be bounded (we explain bounds later in this article).
  // - Generic methods can have different type parameters separated by commas in the method
  // signature.
  // - Method body for a generic method is just like a normal method.
  // The <T> in the method signature implies that the method will be dealing with generic type T.
  // This is needed even if the method is returning void.
  public static <T> List<T> fromArrayToList(T[] aList) {
    return Arrays.stream(aList).toList();
  }

  // As mentioned, the method can deal with more than one generic type. Where this is the case, we
  // must add all generic types to the method signature.
  // Here is how we would handle with type T and type G
  public static <T, G> List<G> fromArrayToList(T[] aList, Function<T, G> mapperFunction) {
    return Arrays.stream(aList).map(mapperFunction).toList();
  }

  // Type parameters can be bounded. Bounded means “restricted,” and we can restrict the types that
  // a method accepts.
  // For example, we can specify that a method accepts a type and all its subclasses (upper bound)
  // or a type and all its superclasses (lower bound).
  // To declare an upper-bounded type, we use the keyword extends after the type, followed by the
  // upper bound that we want to use
  public static <T extends Number> List<T> upperBoundedFromArrayToList(T[] aList) {
    return Arrays.stream(aList).toList();
  }

  // Wildcards are represented by the question mark ? in Java, and we use them to refer to an
  // unknown type. Wildcards are particularly useful with generics and can be used as a parameter
  // type.
  // But first, there is an important note to consider. We know that Object is the supertype of all
  // Java classes. However, a collection of Object is not the supertype of any collection.
  // For example, a List<Object> is not the supertype of List<String>, and assigning a variable of
  // type List<Object> to a variable of type List<String> will cause a compiler error. This is to
  // prevent possible conflicts that can happen if we add heterogeneous types to the same
  // collection.
  // The same rule applies to any collection of a type and its subtypes.
  // This method will work with type Number and all its subtypes. This is called an upper-bounded
  // wildcard, where type Number is the upper bound.
  public static int upperBoundedWildCardExample(List<? extends Number> aList) {
    return aList.stream().mapToInt(Number::intValue).sum();
  }

  // We can also specify wildcards with a lower bound, where the unknown type has to be a supertype
  // of the specified type.
  // Lower bounds can be specified using the super keyword followed by the specific type. For
  // example, <? super T> means unknown type that is a superclass of T (= T and all its parents).
  public static List<?> lowerBoundedWildCardExample(List<? super Integer> aList) {
    if (aList.size() % 2 == 0) {
      return aList.stream().map(Object::toString).map(String::length).toList();
    } else {
      return aList.stream().map(Object::hashCode).map(GenericExamples::integerToHex).toList();
    }
  }

  static final String digits = "0123456789ABCDEF";

  private static String integerToHex(int input) {
    if (input <= 0) return "0";
    StringBuilder hex = new StringBuilder();
    while (input > 0) {
      int digit = input % 16;
      hex.insert(0, digits.charAt(digit));
      input = input / 16;
    }
    return hex.toString();
  }
}
