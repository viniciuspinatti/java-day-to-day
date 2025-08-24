package org.viniciuspinatti.utils;

import java.util.ArrayList;
import java.util.List;

public class GenericsPrimitiveDatatypes {
  // One restriction of generics in Java is that the type parameter cannot be a primitive type
  // If you uncomment the addPrimitiveToList method will receive a compilation error
  //    private void addPrimitiveToList(){
  //        List<int> list = new ArrayList<>();
  //        list.add(17);
  //    }

  /**
   * To understand why primitive data types don’t work, let’s remember that generics are a
   * compile-time feature, meaning the type parameter is erased and all generic types are
   * implemented as type Object.
   *
   * <p>Let’s look at the add method of a list:
   */
  private void addPrimitiveToList() {
    List<Integer> list = new ArrayList<>();
    list.add(17);
    // The signature of the add method is
    //        boolean add(E e);
    //        and will be compiled to:
    //
    //        boolean add(Object e);
    // Therefore, type parameters must be convertible to Object. Since primitive types don’t extend
    // Object, we can’t use them as type parameters.
  }
}
