package com.github.jinahya.junit.jupiter.params.provider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;

public final class JinahyaArgumentsUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static Arguments merge(final Arguments first, final Arguments second, final Arguments... others) {
        if (first == null) {
            throw new NullPointerException("arguments is null");
        }
        if (second == null) {
            throw new NullPointerException("other is null");
        }
        if (others == null) {
            throw new NullPointerException("others is null");
        }
        int length = first.get().length + second.get().length;
        for (final Arguments other : others) {
            length += other.get().length;
        }
        final Object[] arguments = Arrays.copyOf(first.get(), length);
        int index = first.get().length;
        System.arraycopy(second.get(), 0, arguments, index, second.get().length);
        index += second.get().length;
        for (final Arguments other : others) {
            System.arraycopy(other.get(), 0, arguments, index, other.get().length);
            index += other.get().length;
        }
        return Arguments.of(arguments);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaArgumentsUtils() {
        super();
    }
}
