package ru.spbu.apcyb.svp.tasks.task2;

import java.util.EmptyStackException;
import java.util.Stack;

public class CustomStack<T> extends Stack<T> {

    private final transient CustomList<T> value;
    private int size;

    public CustomStack() {
        this.value = new CustomList<>();
        this.size = 0;
    }

    public CustomStack(Object[] list) {
        this.value = new CustomList<>(list);
        this.size = value.size();
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized T push(T elem) {
        value.add(0, elem);
        ++size;
        return elem;
    }

    @Override
    public synchronized T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        --size;
        return value.remove(0);
    }

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return value.get(0);
    }
}
