package voot.util;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class StreamUtils {

    private StreamUtils() {
    }

    public static <T> Optional<T> optionalFromOptionalList(List<Optional<T>> list) {
        return CollectionUtils.isEmpty(list) ? Optional.empty() : list.get(0);
    }
}
