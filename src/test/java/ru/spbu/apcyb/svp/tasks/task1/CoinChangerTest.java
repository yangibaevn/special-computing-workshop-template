package ru.spbu.apcyb.svp.tasks.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import java.io.*;

class CoinChangerTest {
    @Test
    @DisplayName("Test countChangeOptions with valid input")
    void testCountChangeOptionsWithValidInput() {
        assertCountChangeOptions("5\n3 2", 1);
        assertCountChangeOptions("4\n2 1", 3);
        assertCountChangeOptions("19\n3 1", 7);
        assertCountChangeOptions("10\n10", 1);
        assertCountChangeOptions("1000\n1", 1);
        assertCountChangeOptions("5\n10 6", 0);
        assertCountChangeOptions("5\n1 1", 1);
        assertCountChangeOptions("1000\n500 1", 3);
    }

    @Test
    @DisplayName("Test countChangeOptions with invalid input")
    void testCountChangeOptionsWithInvalidInput() {
        exceptionThrowingTest("abc\n", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integer for sum.");
        exceptionThrowingTest("-10\n2 1", "Amount for change should be a positive integer.");
        exceptionThrowingTest("\n2 1", "Input cannot be empty. Please enter a valid positive integer");
        exceptionThrowingTest("7+3\n2 1", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integer for sum.");
        exceptionThrowingTest("3000000000\n5 3", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integer for sum.");
        exceptionThrowingTest("5\n ", "There should be at least 1 available denomination for change.");
        exceptionThrowingTest("30\n-2 1","Change options should be positive integers.");
        exceptionThrowingTest("5\na b", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integers for change");
        exceptionThrowingTest("5\n 1+2", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integers for change");
        exceptionThrowingTest("100\n 3000000000 1", "Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integers for change");
    }

    private void assertCountChangeOptions(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CoinChanger changer = new CoinChanger();
        assertEquals(expectedOutput, changer.countChangeOptions());
    }

    private void exceptionThrowingTest(String inputString, String expectedExceptionMessage) {
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Exception e = assertThrows(RuntimeException.class, () -> {
            CoinChanger changer = new CoinChanger();
        });
        assertEquals(expectedExceptionMessage, e.getMessage());
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(System.in);
    }
}
