package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
//Вывести все четные числа в диапазоне от 1 до 100,
    public static void infinite() {
        Stream<Integer> num = Stream.iterate(1, n -> n + 1);
        num
                .limit(100)
                .forEach(System.out::println);
    }
//Посчитать сумму чисел в массиве [1, 2, 3, 4, 5], используя reduce()
    public static void sum(){
        int[] arrays = {1,2,3,4,5};
        int sum;
        sum = Arrays.stream(arrays).boxed().reduce(0,(a,b)->a+b);
        System.out.println(sum);
    }
//Найти первый четный элемент в списке [1, 2, 3, 4, 5],
public static void findFirstEven(){
    int[] arrays = {1,2,3,4,5};
    Optional<Integer> first = Arrays.stream(arrays)
            .boxed()
            .filter(n->n%2==0)
            .findFirst();
    System.out.println(first);
    }
//Отсортировать элементы массива [1, 3, 5, 7, 9] по возрастанию,
public static void sort(){
    int[] arrays = {1,3,5,7,9};
    Stream<Integer> stream = Arrays.stream(arrays).boxed();
          stream
                  .sorted()
                  .forEach(System.out::println);
    }
//Пропустить первые 10 элементов списка [0, 1, 2,.., 99] и начать выводить с 11-го элемента, выводя каждый 10-й элемент
public static void eachTens() {
    Stream<Integer> stream = Stream.iterate(1, n -> n + 1);
    stream
            .limit(99)
            .skip(10)
            .filter(n->n%10==1)
            .forEach(System.out::println);
    }
//Создать два стрима: один из чисел от 0 до 10, другой из чисел от 10 до 20. Объединить их в один стрим и вывести на экран числа больше 10.
    public static void concatTwo() {
        Stream<Integer> streamFirst = Stream.iterate(0, n -> n + 1).limit(10);
        Stream<Integer> streamSecond = Stream.iterate(10, n -> n + 1).limit(20);
        Stream<Integer> streamFinal = Stream.concat(streamFirst,streamSecond);
            streamFinal
                .filter(n->n>10)
                .forEach(System.out::println);
    }
//Создать стрим четных чисел от 2 до 40 и вывести на экран количество элементов в этом стриме.
    public static void counter() {
       long count = Stream.iterate(2, n -> n + 2)
                .limit(40)
                .count();
       System.out.println(count);
    }
//Разделить элементы Stream на две группы: четные и нечетные, вывести результаты.
    public static void devideOnTwoParts(){
       Stream<Integer> numbers = Stream.iterate(1,n->n+1).limit(10);
        Map<Boolean, List<Integer>> twoParts = numbers.collect(Collectors.groupingBy(n->n%2==0));
    }
}
