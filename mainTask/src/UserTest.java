import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("John", "Doe", 30);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(30, user.getAge());
    }

    @Test
    void testInvalidAge() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User("John", "Doe", -5);
        });
        assertEquals("Некорректный возраст: John", exception.getMessage());
    }
}

