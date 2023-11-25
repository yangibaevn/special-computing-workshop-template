package ru.spbu.apcyb.svp.tasks.task1;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangerTest {
    private final InputStream sysIn = System.in;
    private final PrintStream sysOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private CoinChanger changer;

    @BeforeEach
    void prepare() {
        changer = new CoinChanger();
    }

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Test countChangeOptions with valid input")
    void testCountChangeOptionsWithValidInput() {
        assertCountChangeOptions("5\n3 2", 1);
        assertCountChangeOptions("4\n2 1", 3);
        assertCountChangeOptions("19\n3 1", 7);
        assertCountChangeOptions("0\n3 2 1", 1);
        assertCountChangeOptions("10\n10", 1);
        assertCountChangeOptions("10\n0", 0);
    }

    @Test
    @DisplayName("Test countChangeOptions with invalid input")
    void testCountChangeOptionsWithInvalidInput() {
        assertInvalidInputHandling("abc\n", "Invalid input. Please enter a valid integer.");
        assertInvalidInputHandling("-10\n2 1", "Invalid amount. Please enter a non-negative integer.");
        assertInvalidInputHandling("", "Input cannot be empty.");
    }

    private void assertCountChangeOptions(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals(expectedOutput, changer.countChangeOptions());
    }

    private void assertInvalidInputHandling(String input, String expectedErrorMessage) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals(expectedErrorMessage, outContent.toString().trim());
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(sysIn);
        System.setOut(sysOut);
    }
}
