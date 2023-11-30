package ru.spbu.apcyb.svp.tasks.task2;

import java.util.EmptyStackException;
import java.util.Stack;

public class CustomStack<T> extends Stack<T> {

    private final CustomList<T> value;

    public CustomStack() {
        this.value = new CustomList<>();
    }

    public CustomStack(Object[] list) {
        this.value = new CustomList<>(list);
    }

    @Override
    public synchronized int size() {
        return value.size();
    }

    @Override
    public synchronized T push(T elem) {
        value.add(0, elem);
        return elem;
    }

    @Override
    public synchronized T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return value.remove(0);
    }

    @Override
    public synchronized boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public synchronized T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return value.get(0);
    }
}