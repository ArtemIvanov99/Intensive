package collections;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TasksTest {
    @org.junit.jupiter.api.Test
    void testIsSorted() {
        Tasks tasks = new Tasks();

        int[] sortedArray = {1, 2, 3, 4, 5};
        tasks.isSorted(sortedArray); // Должно вывести "Array is sorted."

        int[] unsortedArray = {5, 3, 1, 4, 2};
        tasks.isSorted(unsortedArray); // Должно вывести "Please, try again."
    }
@org.junit.jupiter.api.Test
    void testSwapFirstAndLast() {
        Tasks tasks = new Tasks();

        int[] array = {1, 2, 3, 4, 5};
        tasks.swapFirstAndLast(array); // Должно вывести массив с первым и последним элементами, поменявшимися местами

        assertArrayEquals(new int[]{5, 2, 3, 4, 1}, array); // Проверяем, что элементы поменялись
    }

@org.junit.jupiter.api.Test
    void testFindUnique() {
        Tasks tasks = new Tasks();

        int[] array = {1, 2, 3, 4, 2, 3, 5};
        tasks.findUnique(array); // Должно вывести "1 is first unique"

        int[] arrayWithNoUnique = {1, 1, 2, 2, 3, 3};
        tasks.findUnique(arrayWithNoUnique); // Ничего не выведет, так как нет уникальных элементов
    }
}
