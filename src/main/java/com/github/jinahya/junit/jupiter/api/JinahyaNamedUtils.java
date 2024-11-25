package com.github.jinahya.junit.jupiter.api;

import org.junit.jupiter.api.Named;

import java.util.Objects;

/**
 * Utilities for {@link Named}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JinahyaNamedUtils {

    public static <T> T payload(final Object argument) {
        Objects.requireNonNull(argument, "argument is null");
        if (argument instanceof Named<?>) {
            return payload(((Named<?>) argument).getPayload());
        }
        @SuppressWarnings({"unchecked"})
        final var cast = (T) argument;
        return cast;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaNamedUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
