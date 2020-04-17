package com.github.jinahya.junit.jupiter.params.provider;

import java.util.stream.IntStream;

public class CharacterRadixProvider {

    /**
     * Returns an int stream of closed range from {@link Character#MIN_RADIX} to {@link Character#MAX_RADIX}.
     *
     * @return an int stream of closed range from {@link Character#MIN_RADIX} to {@link Character#MAX_RADIX}.
     */
    public static IntStream radices() {
        return IntStream.rangeClosed(Character.MIN_RADIX, Character.MAX_RADIX);
    }
}
