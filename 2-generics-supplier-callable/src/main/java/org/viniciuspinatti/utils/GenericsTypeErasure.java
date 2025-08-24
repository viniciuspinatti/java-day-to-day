package org.viniciuspinatti.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generics were added to Java to ensure type safety. And to ensure that generics won’t cause
 * overhead at runtime, the compiler applies a process called type erasure on generics at compile
 * time. Type erasure removes all type parameters and replaces them with their bounds or with Object
 * if the type parameter is unbounded. This way, the bytecode after compilation contains only normal
 * classes, interfaces and methods, ensuring that no new types are produced. Proper casting is
 * applied as well to the Object type at compile time.
 */
public class GenericsTypeErasure {

  // Generic declaration example
  public <T> List<T> genericMethod(List<T> list) {
    return list.stream().collect(Collectors.toList());
  }

  // for illustration
  //    public List<Object> withErasure(List<Object> list) {
  //        return list.stream().collect(Collectors.toList());
  //    }

  // which in practice results in
  //    public List withErasure(List list) {
  //        return list.stream().collect(Collectors.toList());
  //    }

  //    If the type is bounded, the type will be replaced by the bound at compile time:
  //
  //    public <T extends Building> void genericMethod(T t) {
  //    ...
  //    }

  //    and would change after compilation:
  //
  //    public void genericMethod(Building t) {
  //    ...
  //    }
}
