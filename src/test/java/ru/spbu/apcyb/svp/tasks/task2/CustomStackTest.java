package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

class CustomStackTest {
    private CustomStack<Integer> customStack;

    @BeforeEach
    void setUp() {
        customStack = new CustomStack<>();
    }

    @Test
    void testPushAndPop() {
        assertTrue(customStack.isEmpty());
        customStack.push(1);
        customStack.push(2);
        customStack.push(3);

        assertEquals(3, customStack.size());
        assertFalse(customStack.isEmpty());

        assertEquals(3, customStack.pop());
        assertEquals(2, customStack.pop());
        assertEquals(1, customStack.pop());

        assertTrue(customStack.isEmpty());
    }

    @Test
    void testPopEmptyStack() {
        assertTrue(customStack.isEmpty());
        assertThrows(EmptyStackException.class, () -> customStack.pop());
    }

    @Test
    void testPeek() {
        assertTrue(customStack.isEmpty());
        customStack.push(42);
        customStack.push(17);
        customStack.push(11);

        assertEquals(3, customStack.size());
        assertEquals(11, customStack.peek());

        assertEquals(3, customStack.size());
    }

    @Test
    void testPeekEmptyStack() {
        assertTrue(customStack.isEmpty());
        assertThrows(EmptyStackException.class, () -> customStack.peek());
    }

    @Test
    void testIsEmpty() {
        assertTrue(customStack.isEmpty());
        customStack.push(100);
        assertFalse(customStack.isEmpty());
        customStack.pop();
        assertTrue(customStack.isEmpty());
    }
}
