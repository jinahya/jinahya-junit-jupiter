package com.github.jinahya.junit.jupiter.params.provider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;

public final class JinahyaArgumentsUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static Arguments merge(final Arguments first, final Arguments second) {
        if (first == null) {
            throw new NullPointerException("first is null");
        }
        if (second == null) {
            throw new NullPointerException("second is null");
        }
        final Object[] arguments1 = first.get();
        final Object[] arguments2 = second.get();
        final Object[] arguments = Arrays.copyOf(arguments1, arguments1.length + arguments2.length);
        System.arraycopy(arguments2, 0, arguments, arguments.length, arguments2.length);
        return Arguments.of(arguments);
    }

    public static Arguments merge(Arguments first, final Arguments second, final Arguments... others) {
        if (first == null) {
            throw new NullPointerException("arguments is null");
        }
        if (second == null) {
            throw new NullPointerException("other is null");
        }
        if (others == null) {
            throw new NullPointerException("others is null");
        }
        first = merge(first, second);
        for (final Arguments other : others) {
            first = merge(first, other);
        }
        return first;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaArgumentsUtils() {
        super();
    }
}
