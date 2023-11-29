package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class CustomListTest {

    private CustomList<Integer> customList;

    @BeforeEach
    void setUp() {
        customList = new CustomList<>();
        customList.add(1);
        customList.add(2);
        customList.add(3);
    }

    @Test
    void testSize() {
        assertEquals(3, customList.size());
    }

    @Test
    void testGet() {
        assertEquals(2, customList.get(1));
    }

    @Test
    void testSet() {
        assertEquals(3, customList.set(2, 4));
        assertEquals(4, customList.get(2));
    }

    @Test
    void testAdd() {
        customList.add(5);
        assertEquals(4, customList.size());
        assertEquals(5, customList.get(3));
    }

    @Test
    void testRemove() {
        customList.remove(Integer.valueOf(2));
        assertEquals(2, customList.size());
        assertFalse(customList.contains(2));
    }

    @Test
    void testAddAll() {
        List<Integer> anotherList = Arrays.asList(4, 5, 6);
        customList.addAll(anotherList);
        assertEquals(6, customList.size());
        assertTrue(customList.containsAll(anotherList));
    }

    @Test
    void testIndexOf() {
        assertEquals(1, customList.indexOf(2));
        assertEquals(-1, customList.indexOf(5));
    }

    @Test
    void testLastIndexOf() {
        customList.add(2);
        assertEquals(3, customList.lastIndexOf(2));
        assertEquals(-1, customList.lastIndexOf(11));
    }

    @Test
    void testClear() {
        customList.clear();
        assertEquals(0, customList.size());
        assertTrue(customList.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertFalse(customList.isEmpty());
        customList.clear();
        assertTrue(customList.isEmpty());
    }

    @Test
    void testContains() {
        assertTrue(customList.contains(1));
        assertFalse(customList.contains(5));
    }

    @Test
    void testRemoveAll() {
        List<Integer> removeList = Arrays.asList(2, 3);
        assertTrue(customList.removeAll(removeList));
        assertEquals(1, customList.size());
        assertFalse(customList.containsAll(removeList));
    }

    @Test
    void testContainsAll() {
        List<Integer> containsList = Arrays.asList(1, 2);
        assertTrue(customList.containsAll(containsList));
        List<Integer> notContainsList = Arrays.asList(1, 5);
        assertFalse(customList.containsAll(notContainsList));
    }

    // Тесты для неподдерживаемых операций
    @Test
    void testUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class, () -> customList.addAll(1, Arrays.asList(4, 5)));
        assertThrows(UnsupportedOperationException.class, () -> customList.iterator());
        assertThrows(UnsupportedOperationException.class, () -> customList.listIterator());
        assertThrows(UnsupportedOperationException.class, () -> customList.listIterator(2));
        assertThrows(UnsupportedOperationException.class, () -> customList.subList(0, 2));
        assertThrows(UnsupportedOperationException.class, () -> customList.retainAll(Arrays.asList(1, 2)));
        assertThrows(UnsupportedOperationException.class, () -> customList.toArray(new Integer[0]));
    }
}
