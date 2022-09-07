package ru.spbu.apcyb.svp.tasks;

/**
 * Пример задания.
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * Печатает массив в `stdout` в формате `[1, 2, 3]`. Не добавляет переносов строк.
     * <br>
     * Цена: 1 балл
     *
     * @param array массив для печати
     */
    public static void printArray(int[] array) {
    }

    /**
     * Печатает массив в `stdout` в формате `[\n[1, 2, 3],\n[1, 2],\n[1, 2, 3, 4]\n]`. \n - это переносы
     * строк.
     * Для создания переноса строк используйте System.out.println(), не печатайте \n напрямую. Это
     * помешает нашим тестам выполняться на Windows.
     * <br>
     * Если array.length == 0, то должно напечатать `[]` без переносов строк.
     * <br>
     * Если array.length == 1, то должно напечатать в формате `[[1, 2, 3]]` без переносов.
     * <br>
     * <br>
     * Цена: 1 балл
     *
     * @param array массив для печати
     */
    public static void printArray(int[][] array) {
    }

    /**
     * Создать копию промежутка из оригинального массива.
     * <br>
     * Требования функции: array != null, 0 <= fromInclusive <= toExclusive <= array.length.
     * <br>
     * Цена: 1 балл
     *
     * @param array         оригинальный массив.
     * @param fromInclusive начальный индекс промежутка.
     * @param toExclusive   конечный индекс промежутка (не включительно).
     * @return копия промежутка [fromInclusive, toExclusive)
     */
    public static int[] subArray(int[] array, int fromInclusive, int toExclusive) {
        return array;
    }

    /**
     * Разбивает массив на несколько равных кусков.
     * <br>
     * Цена: 1 балл
     *
     * @param array     исходный массив
     * @param chunkSize размер одного куска
     * @return массив из отдельных кусков
     */
    public static int[][] chunked(int[] array, int chunkSize) {
        return new int[][]{array};
    }

    /**
     * Объединить два отсортированных массива в один отсортированный массив.
     * <br>
     * Цена: 1 балл
     *
     * @param left  первый отсортированный массив.
     * @param right второй отсортированный массив.
     * @return массив, объединенный из left и right.
     */
    public static int[] mergeSortedArrays(int[] left, int[] right) {
        return left;
    }

    /**
     * Создает копию массива array и сортирует её, используя сортировку слиянием.
     * <br>
     * Цена: 2 баллa
     *
     * @param array исходный массив.
     * @return копию исходного массива, но с отсортированными элементами.
     */
    public static int[] sorted(int[] array) {
        return array;
    }

    /**
     * Переворачивает содержимое массива `array` на промежутке [fromInclusive, toExclusive).
     * <br>
     * Требования функции: array != null, 0 <= fromInclusive <= toExclusive <= array.length.
     * <br>
     * Функция должна работать за O(toExclusive - fromExclusive)
     * и не выделять дополнительной памяти (не использовать оператор <code>new</code>).
     * <br>
     * Цена: 1 балл
     *
     * @param array         массив, который нужно перевернуть.
     * @param fromInclusive индекс начала промежутка.
     * @param toExclusive   индекс конца промежутка (не включительно).
     */
    public static void reverseRange(int[] array, int fromInclusive, int toExclusive) {
    }

    /**
     * Делает циклический сдвиг содержимого массива на steps шагов вправо.
     * <br>
     * Функция должна поддерживать отрицательное количество шагов, работать за O(N)
     * и не выделять дополнительной памяти (не использовать оператор <code>new</code>).
     * <br>
     * Примеры:
     * <ul>
     * <li>rotate({1, 2, 3}, 0) -> {1, 2, 3}</li>
     * <li>rotate({1, 2, 3}, 0) -> {1, 2, 3}</li>
     * <li>rotate({1, 2, 3}, 1) -> {3, 1, 2}</li>
     * <li>rotate({1, 2, 3}, -1) -> {2, 3, 1}</li>
     * <li>rotate({1, 2, 3}, 3) -> {1, 2, 3}</li>
     * <li>rotate({1, 2, 3}, 3001) -> {3, 1, 2}</li>
     * </ul>
     * <br>
     * Требования функции: array != null.
     * <br>
     * Цена: 2 балла
     *
     * @param array массив, на котором нужно сделать циклический сдвиг.
     * @param steps количество шагов, на которое нужно сделать сдвиг.
     */
    public static void rotate(int[] array, int steps) {
    }
}
