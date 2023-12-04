package ru.spbu.apcyb.svp.tasks.task2;
import java.util.*;

public class CustomList<T> implements List<T> {

    private static final String IOOB = "Index is out of bounds";
    private Object[] array;
    private int size;

    public CustomList() {
        this(10);
    }

    public CustomList(int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    public CustomList(Object[] list) {
        this.size = list.length;
        this.array = increaseCapacity();
        System.arraycopy(list, 0, this.array, 0, list.length);
    }

    private Object[] increaseCapacity() {
        if (size >= Integer.MAX_VALUE / 2) {
            return new Object[Integer.MAX_VALUE];
        } else {
            int newCapacity = size * 2;
            if (newCapacity < 0) {
                return new Object[Integer.MAX_VALUE];
            } else {
                return new Object[newCapacity];
            }
        }
    }


    /* Переопределенные методы */
    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(IOOB);
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(IOOB);
        }
        Object obj = array[index];
        array[index] = element;
        return (T) obj;
    }

    @Override
    public boolean add(T element) {
        if (size == array.length) {
            array = increaseCapacity();
        }
        array[size++] = element;
        return true;
    }


    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(IOOB);
        }
        if (array.length == size) {
            array = increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(IOOB);
        }
        Object removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return (T) removedElement;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T obj : c) {
            add(obj);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(o, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        array = new Object[10];
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return array;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object obj : c) {
            remove(obj);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomList<?> customList)) return false;
        return size == customList.size && Arrays.equals(array, customList.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(increaseCapacity());
        return result;
    }


    /* Остальные методы оставлены без реализации */

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("'addAll' at specific index is unsupported operation");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("'iterator' is unsupported operation");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("'listIterator' is unsupported operation");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("'listIterator' is unsupported operation");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("'subList' is unsupported operation");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("'retainAll' is unsupported operation");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("'toArray(T[] a)' is unsupported operation");
    }

}
