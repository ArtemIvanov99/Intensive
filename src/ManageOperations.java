import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageOperations {
    private final Connection connection;
    public ManageOperations(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод для добавления новой книги в таблицу books
     * @param title Название книги. Не может быть null или пустым
     * @param authorId ID автора из таблицы authors
     * @param publishedYear Год издания (может быть null)
     * @throws SQLException если произошла ошибка при работе с БД
     * @throws IllegalArgumentException если неверные аргументы
     */
    public void addBook(String title, int authorId, Integer publishedYear) throws SQLException {
        // Проверка входных параметров
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (authorId <= 0) {
            throw new IllegalArgumentException("ID автора должен быть положительным числом");
        }

        String sql = "INSERT INTO books (title, author_id, published_year) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setInt(2, authorId);

            if (publishedYear != null) {
                if (publishedYear <= 0) {
                    throw new IllegalArgumentException("Год издания должен быть положительным числом");
                }
                statement.setInt(3, publishedYear);
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            statement.executeUpdate();
            System.out.println("Книга '" + title + "' успешно добавлена в базу данных");
        }
    }
    /**
     * Метод для добавления нового читателя в таблицу readers
     * @param firstName Имя читателя. Не может быть null или пустым
     * @param lastName Фамилия читателя. Не может быть null или пустым
     * @param email Email читателя (не может быть null или пустым, должен содержать @)
     * @throws SQLException если произошла ошибка при работе с БД
     * @throws IllegalArgumentException если неверные аргументы
     */
    public void addReader(String firstName, String lastName, String email) throws SQLException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя читателя не может быть пустым");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия читателя не может быть пустой");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email должен быть валидным и содержать @");
        }

        String sql = "INSERT INTO readers (first_name, last_name, email) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName.trim());
            statement.setString(2, lastName.trim());
            statement.setString(3, email.trim());

            statement.executeUpdate();
            System.out.println("Читатель " + firstName + " " + lastName + " успешно добавлен в базу данных");
        }
    }
    /**
     * Метод для извлечения всех книг из таблицы books
     * @return Список книг в формате "ID. Название (Год) [Автор ID]"
     * @throws SQLException если произошла ошибка при работе с БД
     */
    public List<String> getAllBooks() throws SQLException {
        List<String> books = new ArrayList<>();
        String sql = "SELECT id, title, published_year, author_id FROM books";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                int year = resultSet.getInt("published_year");

                String bookInfo = String.format("%d. %s (%d) [Автор: %d]",
                        id, title, year, authorId);
                books.add(bookInfo);
            }
        }
        return books;
    }
    /**
     * Метод для извлечения всех читателей из таблицы readers
     * @return Список читателей в формате "ID. Имя Фамилия (Email)"
     * @throws SQLException если произошла ошибка при работе с БД
     */
    public List<String> getAllReaders() throws SQLException {
        List<String> readers = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, email FROM readers";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                String readerInfo = String.format("%d. %s %s (%s)",
                        id, firstName, lastName, email);
                readers.add(readerInfo);
            }
        }
        return readers;
    }

    /**
     * Метод для извлечения всех авторов из таблицы authors
     * @return Список авторов в формате "ID. Имя Фамилия"
     * @throws SQLException если произошла ошибка при работе с БД
     */
    public List<String> getAllAuthors() throws SQLException {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name FROM authors";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                String authorInfo = String.format("%d. %s %s",
                        id, firstName, lastName);
                authors.add(authorInfo);
            }
        }
        return authors;
    }
    /**
     * Метод для обновления информации о книге по её id
     * @param bookId ID книги для обновления (должен быть > 0)
     * @param newTitle Новое название книги (если null - не обновляется)
     * @param newAuthorId Новый ID автора (если null - не обновляется)
     * @param newPublishedYear Новый год издания (если null - не обновляется)
     * @throws SQLException если произошла ошибка при работе с БД
     * @throws IllegalArgumentException если неверные аргументы
     */
    public void updateBook(int bookId, String newTitle, Integer newAuthorId, Integer newPublishedYear)
            throws SQLException {
        // Проверка ID книги
        if (bookId <= 0) {
            throw new IllegalArgumentException("ID книги должен быть положительным числом");
        }

        // Проверка, что хотя бы одно поле для обновления задано
        if (newTitle == null && newAuthorId == null && newPublishedYear == null) {
            throw new IllegalArgumentException("Должно быть указано хотя бы одно поле для обновления");
        }

        // Проверка новых значений
        if (newTitle != null && newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (newAuthorId != null && newAuthorId <= 0) {
            throw new IllegalArgumentException("ID автора должен быть положительным числом");
        }
        if (newPublishedYear != null && newPublishedYear <= 0) {
            throw new IllegalArgumentException("Год издания должен быть положительным числом");
        }

        // Формируем SQL запрос динамически
        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<Object> params = new ArrayList<>();

        if (newTitle != null) {
            sql.append("title = ?, ");
            params.add(newTitle.trim());
        }
        if (newAuthorId != null) {
            sql.append("author_id = ?, ");
            params.add(newAuthorId);
        }
        if (newPublishedYear != null) {
            sql.append("published_year = ?, ");
            params.add(newPublishedYear);
        }

        // Удаляем последнюю запятую и пробел
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE id = ?");
        params.add(bookId);

        // Выполняем запрос
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Книга с ID " + bookId + " не найдена");
            } else {
                System.out.println("Информация о книге с ID " + bookId + " успешно обновлена");
            }
        }
    }
    /**
     * Метод для удаления книги по её id
     * @param bookId ID книги для удаления (должен быть > 0)
     * @throws SQLException если произошла ошибка при работе с БД
     * @throws IllegalArgumentException если неверный ID
     */
    public void deleteBook(int bookId) throws SQLException {
        // Проверка ID книги
        if (bookId <= 0) {
            throw new IllegalArgumentException("ID книги должен быть положительным числом");
        }

        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("Книга с ID " + bookId + " не найдена");
            } else {
                System.out.println("Книга с ID " + bookId + " успешно удалена");
            }
        }
    }
    /**
     * Метод, который добавляет книгу и автора в одну транзакцию, в случае ошибки откатить изменения
     * @param authorFirstName Имя автора (не может быть null или пустым)
     * @param authorLastName Фамилия автора (не может быть null или пустым)
     * @param bookTitle Название книги (не может быть null или пустым)
     * @param publishedYear Год издания (может быть null)
     * @throws SQLException если произошла ошибка при работе с БД
     * @throws IllegalArgumentException если неверные аргументы
     */
    public void addAuthorWithBook(String authorFirstName, String authorLastName,
                                  String bookTitle, Integer publishedYear) throws SQLException {
        // Проверка входных параметров
        if (authorFirstName == null || authorFirstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя автора не может быть пустым");
        }
        if (authorLastName == null || authorLastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия автора не может быть пустой");
        }
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        if (publishedYear != null && publishedYear <= 0) {
            throw new IllegalArgumentException("Год издания должен быть положительным числом");
        }

        // Отключаем auto-commit для управления транзакцией вручную
        boolean originalAutoCommit = connection.getAutoCommit();
        try {
            connection.setAutoCommit(false);

            // 1. Добавляем автора
            String insertAuthorSql = "INSERT INTO authors (first_name, last_name) VALUES (?, ?)";
            int authorId;

            try (PreparedStatement authorStmt = connection.prepareStatement(
                    insertAuthorSql, Statement.RETURN_GENERATED_KEYS)) {
                authorStmt.setString(1, authorFirstName.trim());
                authorStmt.setString(2, authorLastName.trim());
                authorStmt.executeUpdate();

                // Получаем сгенерированный ID автора
                try (ResultSet generatedKeys = authorStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        authorId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Не удалось получить ID созданного автора");
                    }
                }
            }

            // 2. Добавляем книгу этого автора
            String insertBookSql = "INSERT INTO books (title, author_id, published_year) VALUES (?, ?, ?)";

            try (PreparedStatement bookStmt = connection.prepareStatement(insertBookSql)) {
                bookStmt.setString(1, bookTitle.trim());
                bookStmt.setInt(2, authorId);

                if (publishedYear != null) {
                    bookStmt.setInt(3, publishedYear);
                } else {
                    bookStmt.setNull(3, Types.INTEGER);
                }

                bookStmt.executeUpdate();
            }

            // Если всё успешно - коммитим транзакцию
            connection.commit();
            System.out.printf("Автор %s %s и книга '%s' успешно добавлены%n",
                    authorFirstName, authorLastName, bookTitle);

        } catch (SQLException e) {
            // В случае ошибки - откатываем транзакцию
            try {
                connection.rollback();
                System.out.println("Транзакция откачена. Изменения не сохранены.");
            } catch (SQLException rollbackEx) {
                System.err.println("Ошибка при откате транзакции: " + rollbackEx.getMessage());
            }
            throw new SQLException("Ошибка при добавлении автора и книги: " + e.getMessage(), e);
        } finally {
            // Восстанавливаем исходное состояние auto-commit
            try {
                connection.setAutoCommit(originalAutoCommit);
            } catch (SQLException e) {
                System.err.println("Ошибка при восстановлении auto-commit: " + e.getMessage());
            }
        }
    }
    /**
     * Метод для поиска книг по названию или автору
     * @param titlePart Часть названия книги (null - не учитывается)
     * @param authorPart Часть имени автора (null - не учитывается)
     * @return Список найденных книг в формате "ID. Название (Год) [Автор ID]"
     * @throws SQLException если произошла ошибка при работе с БД
     */
    public List<String> searchBooks(String titlePart, String authorPart) throws SQLException {
        List<String> books = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT b.id, b.title, b.published_year, b.author_id FROM books b WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (titlePart != null) {
            sql.append(" AND title LIKE ?");
            params.add("%" + titlePart + "%");
        }

        if (authorPart != null) {
            sql.append(" AND author_id IN (SELECT id FROM authors WHERE first_name LIKE ? OR last_name LIKE ?)");
            params.add("%" + authorPart + "%");
            params.add("%" + authorPart + "%");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i).toString());
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    int year = resultSet.getInt("published_year");
                    int authorId = resultSet.getInt("author_id");

                    books.add(String.format("%d. %s (%d) [Автор: %d]",
                            id, title, year, authorId));
                }
            }
        }
        return books;
    }
}