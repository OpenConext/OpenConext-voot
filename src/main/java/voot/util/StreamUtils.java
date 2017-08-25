package voot.util;

import java.util.List;
import java.util.Optional;

public class StreamUtils {

  public static <T> Optional<T> optionalFromOptionalList(List<Optional<T>> list) {
    return list != null && !list.isEmpty() ? list.get(0) : Optional.empty();
  }
}
