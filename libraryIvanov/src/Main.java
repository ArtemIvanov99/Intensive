import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Инициализация базы данных и подключение
            Connection connection = DatabaseInitializer.initializeDatabase();
            ManageOperations manager = new ManageOperations(connection);

            // Примеры использования методов

            // 1. Добавление автора с книгой
            System.out.println("\n1. Добавление автора с книгой:");
            manager.addAuthorWithBook("Лев", "Толстой", "Война и мир", 1869);

            // 2. Добавление книги
            System.out.println("\n2. Добавление книги:");
            manager.addBook("Анна Каренина", 1, 1877);

            // 3. Добавление читателя
            System.out.println("\n3. Добавление читателя:");
            manager.addReader("Иван", "Иванов", "ivan@example.com");

            // 4. Получение списка книг
            System.out.println("\n4. Список всех книг:");
            manager.getAllBooks().forEach(System.out::println);

            // 5. Получение списка авторов
            System.out.println("\n5. Список всех авторов:");
            manager.getAllAuthors().forEach(System.out::println);

            // 6. Получение списка читателей
            System.out.println("\n6. Список всех читателей:");
            manager.getAllReaders().forEach(System.out::println);

            // 7. Обновление информации о книге
            System.out.println("\n7. Обновление информации о книге:");
            manager.updateBook(1, "Война и мир (том 1)", null, null);

            // 8. Поиск книг
            System.out.println("\n8. Поиск книг по названию 'Анна':");
            manager.searchBooks("Анна", null).forEach(System.out::println);

            // 9. Удаление книги
            System.out.println("\n9. Удаление книги:");
            manager.deleteBook(2);

            connection.close();
        } catch (SQLException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}