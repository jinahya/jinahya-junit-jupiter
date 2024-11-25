package com.github.jinahya.junit.jupiter.params.provider;

import com.github.jinahya.junit.jupiter.api.JinahyaNamedUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    /**
     * Returns a stream of specified arguments' values.
     *
     * @param arguments the arguments.
     * @return a stream of {@code arguments}' values.
     * @see Arguments#get()
     */
    public static Stream<Object> stream(final Arguments arguments) {
        Objects.requireNonNull(arguments, "arguments is null");
        return Arrays.stream(arguments.get());
    }

    public static Stream<Object> payloads(final Arguments arguments) {
        return stream(arguments).map(JinahyaNamedUtils::payload);
    }

    /**
     * Creates a new instance of {@link Arguments} from specified stream's elements.
     *
     * @param stream the stream.
     * @return a new instance of {@link Arguments} with specified stream's elements.
     */
    public static Arguments from(final Stream<?> stream) {
        Objects.requireNonNull(stream, "stream is null");
        return Arguments.of(stream.toArray());
    }

    public static Arguments ofPayloadsMapped(final Arguments arguments,
                                             final IntFunction<? extends Function<Object, Object>> mapper) {
        Objects.requireNonNull(arguments, "arguments is null");
        Objects.requireNonNull(mapper, "mapper is null");
        final var got = arguments.get();
        return from(
                IntStream.range(0, got.length)
                        .mapToObj(i -> mapper.apply(i).apply(JinahyaNamedUtils.payload(got[i])))
        );
    }

    public static Arguments ofPayloads(final Arguments arguments) {
        return ofPayloadsMapped(arguments, i -> Function.identity());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaArgumentsUtils() {
        super();
    }
}
