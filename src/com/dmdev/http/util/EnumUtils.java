package com.dmdev.http.util;

import java.util.Arrays;
import java.util.Optional;

public class EnumUtils {
    // Null-safe find in Enum
    public static <T extends Enum<T>> Optional<T> find(String findValue, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumValue -> enumValue.name().equalsIgnoreCase(findValue))
                .findFirst();
    }
}
