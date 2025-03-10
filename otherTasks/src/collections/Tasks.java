package collections;

import java.util.Arrays;
import java.util.Random;

public class Tasks {

    public void isSorted(int[] array){
            boolean isSorted = true;

            for(int i = 0; i< array.length-1; i++){
                if(array[i]>array[i+1]){
                    isSorted = false;
                    break;
                }
            }
            if(isSorted){
                System.out.println("Array is sorted.");
            } else {
                System.out.println("Please, try again.");
            }
        }

    public void swapFirstAndLast(int[] array){
        for (int i =0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("");

        int first, last;
        first = array[0];
        last = array[array.length-1];
        array[0] = last;
        array[array.length-1] = first;

        for (int i =0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
    }

    public void findUnique(int[] array){
        for (int i=0; i<array.length;i++){
            boolean isUnique = true;
            for(int j =1;j<array.length;j++){
                if(i!=j && array[i]==array[j]){
                    isUnique = false;
                    break;
                }
            }
            if(isUnique) {
                int firstUnique = array[i];
                System.out.println(firstUnique + " is first unique");
                break;
            }
        }
    }


    // Метод для запуска сортировки случайного массива слиянием.
    public static void run() {
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100); // Генерация случайного числа от 0 до 99
        }
        System.out.println("Исходный массив: " + Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

    // Метод для сортировки слиянием
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Находим средний элемент
            int middle = (left + right) / 2;

            // Рекурсивно сортируем левую и правую части
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Объединяем отсортированные части
            merge(array, left, middle, right);
        }
    }
    // Метод для слияния двух подмассивов
    public static void merge(int[] array, int left, int middle, int right) {
        // Находим размеры двух подмассивов, которые нужно объединить
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Создаем временные массивы
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Копируем данные во временные массивы
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }
        // Индексы для временных массивов
        int i = 0, j = 0;
        // Индекс для основного массива
        int k = left;
        // Слияние временных массивов обратно в основной массив
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        // Копируем оставшиеся элементы leftArray, если они есть
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        // Копируем оставшиеся элементы rightArray, если они есть
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
