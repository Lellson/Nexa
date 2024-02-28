package ac.at.uibk.dps.nexa.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Common {

  public static <T> Set<T> getArrayDuplicates(T[] array) {
    var elements = new HashSet<>();
    return Arrays.stream(array)
        .filter(n -> !elements.add(n))
        .collect(Collectors.toSet());
  }
}
