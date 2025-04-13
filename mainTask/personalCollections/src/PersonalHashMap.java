public class PersonalHashMap<K, V> {
    // Внутренний класс для хранения пар ключ-значение
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next; // Для обработки коллизий с помощью цепочек

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Начальный размер массива
    private static final int INITIAL_CAPACITY = 16;

    // Массив бакетов, где хранятся элементы
    private Node<K, V>[] buckets;

    // Количество элементов в HashMap
    private int count = 0;

    // Конструктор
    @SuppressWarnings("unchecked")
    public PersonalHashMap() {
        // Создает массив бакетов с начальной емкостью
        buckets = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
    }

    /**
     * Метод для вычисления индекса бакета по ключу
     * @param key ключ
     * @return индекс в массиве бакетов
     */
    private int getBucketIndex(K key) {
        if (key == null) {
            return 0; // null всегда попадает в первый бакет
        }
        // Вычисляется хэш-код ключа и берется по модулю длины массива
        // Math.abs нужен, чтобы индекс был положительным
        return Math.abs(key.hashCode()) % buckets.length;
    }

    /**
     * Добавляет элемент в PersonalHashMap
     * @param key ключ
     * @param value значение
     */
    public void put(K key, V value) {
        // Получаем индекс бакета для этого ключа
        int bucketIndex = getBucketIndex(key);
        Node<K, V> node = buckets[bucketIndex];

        // Если бакет пустой, просто добавляем новый элемент
        if (node == null) {
            buckets[bucketIndex] = new Node<>(key, value);
            count++;
            return;
        }

        // Ищем элемент с таким же ключом в цепочке
        Node<K, V> prev = null;
        while (node != null) {
            // Проверяем, совпадает ли ключ (учитываем случай, когда key == null)
            if ((key == null && node.key == null) ||
                    (key != null && key.equals(node.key))) {
                // Если ключ найден, обновляем значение
                node.value = value;
                return;
            }
            prev = node;
            node = node.next;
        }

        // Если ключ не найден, добавляем новый элемент в конец цепочки
        prev.next = new Node<>(key, value);
        count++;
    }

    /**
     * Возвращает значение по указанному ключу или null, если PersonalHashMap не содержит такого ключа.
     * @param key ключ
     * @return значение или null, если ключ не найден
     */
    public V get(K key) {
        // Получаем индекс бакета
        int bucketIndex = getBucketIndex(key);
        Node<K, V> node = buckets[bucketIndex];

        // Ищем элемент с таким ключом в цепочке
        while (node != null) {
            if ((key == null && node.key == null) ||
                    (key != null && key.equals(node.key))) {
                return node.value;
            }
            node = node.next;
        }

        // Ключ не найден
        return null;
    }

    /**
     * Удаляет элемент, соответствующий данному ключу, если он присутствует.
     * @param key ключ
     * @return удаленное значение или null, если ключ не найден
     */
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> node = buckets[bucketIndex];
        Node<K, V> prev = null;

        while (node != null) {
            if ((key == null && node.key == null) ||
                    (key != null && key.equals(node.key))) {
                // Нашли элемент для удаления
                if (prev == null) {
                    // Удаляем первый элемент в цепочке
                    buckets[bucketIndex] = node.next;
                } else {
                    // Удаляем элемент из середины или конца цепочки
                    prev.next = node.next;
                }
                count--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }

        // Ключ не найден
        return null;
    }

    /**
     * Удаляет все элементы из PersonalHashMap.
     */
    public void clear() {
        // Просто создаем новый массив бакетов
        buckets = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
        count = 0;
    }
}
