import java.sql.*;
public class DatabaseInitializer {
    public static Connection initializeDatabase() throws SQLException {
        // Параметры подключения
        final String user = "postgres";
        final String password = "123456";
        final String dbName = "library";

        Connection conn;
        try {
            // 1. Пробуем подключиться к целевой БД (library)
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/" + dbName, user, password);
            System.out.println("База данных уже существует, используем её.");
        } catch (SQLException e) {
            // Если БД не существует, подключаемся к postgres и создаём её
            System.out.println("Базы данных нет, создаём...");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/postgres", user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE " + dbName);
            stmt.close();
            conn.close();

            // Подключаемся заново, теперь к новой БД
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/" + dbName, user, password);
            System.out.println("База данных создана!");
        }

        // 2. Создаём таблицы (если их нет)
        createTables(conn);
        return conn;
    }

    private static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS authors (" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(100) NOT NULL," +
                "last_name VARCHAR(100) NOT NULL)");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "author_id INTEGER NOT NULL REFERENCES authors(id)," +
                "published_year INTEGER)");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS readers (" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(100) NOT NULL," +
                "last_name VARCHAR(100) NOT NULL," +
                "email VARCHAR(255) UNIQUE NOT NULL)");
        // Одна книга может быть выдана много раз. Каждая запись ссылается на одну конкретную книгу.
        // Один читатель может взять много книг. Каждая запись ссылается на одного конкретного читателя.
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS book_loans (" +
                "id SERIAL PRIMARY KEY," +
                "book_id INTEGER NOT NULL REFERENCES books(id)," +
                "reader_id INTEGER NOT NULL REFERENCES readers(id)," +
                "loan_date DATE NOT NULL DEFAULT CURRENT_DATE," +
                "return_date DATE," +
                "CONSTRAINT chk_dates CHECK (return_date IS NULL OR return_date >= loan_date))");

        System.out.println("Таблицы проверены/созданы успешно!");
        stmt.close();
    }
}