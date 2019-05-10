package com.wicky.chedam;


import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class SimpleTests {

    @Test
    public void TestFunctionCompose(){
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        Function<Integer, Integer> splitIn2 = (a) -> a/2;
        Function<Integer, Integer> plus100 = (a) -> a + 100;

        // plus100(splitIn2(x))
        // 6/2 + 100 = 103
        Function splitUpThenUseTheResultToPlus100 = splitIn2.andThen(plus100);
        assertEquals(103, splitUpThenUseTheResultToPlus100.apply(6));

        // splitIn2(plus100(x))
        // (6 + 100) / 2 = 53
        Function splitUpTheResultOfPlus100 = splitIn2.compose(plus100);
        assertEquals(53, splitUpTheResultOfPlus100.apply(6));

        // plus100(multiply(a, b))
        // (6 * 6) + 100 = 136
        BiFunction<Integer, Integer, Integer> multiplyAndThenUseTheResultToPlus100 = multiply.andThen(plus100);
        assertEquals(Integer.valueOf(136), multiplyAndThenUseTheResultToPlus100.apply(6, 6));
    }

}