package com.sda.medicalspring.example_tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleParameterTests {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15})
    void shouldReturnTrueForOddNumbers(int number) {
        assertTrue(number % 2 != 0);
    }

    @ParameterizedTest
    @CsvSource({"  test  ,TEST", "tEst ,TEST", "   Java,JAVA"})
    void shouldTrimAndUppercaseInput(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
        // the data.csv file must be in the classpath root, we skip the first line in the file
    void shouldUppercaseAndBeEqualToExpected(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void shouldBeOdd(final Integer number) {
        assertNotEquals(0, number % 2);
    }

    private static Stream<Integer> provideNumbers() {
        return Stream.of(1, 13, 101, 11, 121);
    }

    @ParameterizedTest
    @MethodSource("provideNumbersWithInfoAboutParity")
    void shouldReturnExpectedValue(int number, boolean expected) {
        assertEquals(expected, number % 2 == 1);
    }

    private static Stream<Arguments> provideNumbersWithInfoAboutParity() {
        return Stream.of(Arguments.of(1, true),
                Arguments.of(2, false),
                Arguments.of(10, false),
                Arguments.of(11, true));
    }
}
