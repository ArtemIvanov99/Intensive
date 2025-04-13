public class PersonalHashSet<E> {
    // Внутренний класс для хранения элементов
    private static class Node<E> {
        final E element;
        Node<E> next; // Для обработки коллизий с помощью цепочек

        Node(E element) {
            this.element = element;
        }
    }

    // Начальный размер массива
    private static final int INITIAL_CAPACITY = 16;

    // Массив бакетов, где хранятся элементы
    private Node<E>[] buckets;

    // Количество элементов в HashSet
    private int count = 0;

    // Конструктор
    @SuppressWarnings("unchecked")
    public PersonalHashSet() {
        // Создает массив бакетов с начальной емкостью
        buckets = (Node<E>[]) new Node[INITIAL_CAPACITY];
    }

    /**
     * Метод для вычисления индекса бакета по элементу
     * @param element элемент
     * @return индекс в массиве бакетов
     */
    private int getBucketIndex(E element) {
        if (element == null) {
            return 0; // null всегда попадает в первый бакет
        }
        // Вычисляется хэш-код элемента и берется по модулю длины массива
        return Math.abs(element.hashCode()) % buckets.length;
    }

    /**
     * Добавляет элемент в PersonalHashSet
     * @param element элемент для добавления
     * @return true, если элемент был добавлен, false если уже существовал
     */
    public boolean add(E element) {
        // Получаем индекс бакета для этого элемента
        int bucketIndex = getBucketIndex(element);
        Node<E> node = buckets[bucketIndex];

        // Если бакет пустой, просто добавляется новый элемент
        if (node == null) {
            buckets[bucketIndex] = new Node<>(element);
            count++;
            return true;
        }

        // Ищется элемент в цепочке
        Node<E> prev = null;
        while (node != null) {
            // Проверяем, совпадает ли элемент (учитываем случай, когда element == null)
            if ((element == null && node.element == null) ||
                    (element != null && element.equals(node.element))) {
                // Если элемент найден, возвращается false
                return false;
            }
            prev = node;
            node = node.next;
        }

        // Если элемент не найден, добавляется новый элемент в конец цепочки
        prev.next = new Node<>(element);
        count++;
        return true;
    }

    /**
     * Удаляет элемент из PersonalHashSet
     * @param element элемент для удаления
     * @return true, если элемент был удален, false если не найден
     */
    public boolean remove(E element) {
        int bucketIndex = getBucketIndex(element);
        Node<E> node = buckets[bucketIndex];
        Node<E> prev = null;

        while (node != null) {
            if ((element == null && node.element == null) ||
                    (element != null && element.equals(node.element))) {
                // Нашли элемент для удаления
                if (prev == null) {
                    // Удаляем первый элемент в цепочке
                    buckets[bucketIndex] = node.next;
                } else {
                    // Удаляем элемент из середины или конца цепочки
                    prev.next = node.next;
                }
                count--;
                return true;
            }
            prev = node;
            node = node.next;
        }

        // Элемент не найден
        return false;
    }

    /**
     * Возвращает количество элементов в PersonalHashSet
     * @return количество элементов
     */
    public int size() {
        return count;
    }

    /**
     * Очищает PersonalHashSet, удаляя все элементы
     */
    public void clear() {
        // Создаем новый массив бакетов
        buckets = (Node<E>[]) new Node[INITIAL_CAPACITY];
        count = 0;
    }

    /**
     * Возвращает массив элементов из PersonalHashSet
     * @return массив элементов
     */
    public Object[] toArray() {
        Object[] result = new Object[count];
        int index = 0;

        // Перебираем все бакеты
        for (Node<E> bucket : buckets) {
            Node<E> node = bucket;
            // Перебираем все элементы в цепочке
            while (node != null) {
                result[index++] = node.element;
                node = node.next;
            }
        }
        return result;
    }
}