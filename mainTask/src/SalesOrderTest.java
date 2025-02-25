import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalesOrderTest {

    private User youngUser;
    private User oldUser;
    private ToolList.Tool hummer;
    private ToolList.Tool drill;

    @BeforeEach
    public void setUp() {
        youngUser = new User("John", "Doe", 30);
        oldUser = new User("Jane", "Doe", 65);
        hummer = new ToolList.Tool("Hummer", 100, 10);
        drill = new ToolList.Tool("Drill", 200, 20);
        ToolList.toolHub.put("Hummer", hummer);
        ToolList.toolHub.put("Drill", drill);
    }

    @Test
    public void testGetBasePrice() {
        SalesOrder salesOrder = new SalesOrder(youngUser, "Hummer");
        assertEquals(100, salesOrder.getBasePrice(), "Базовая цена должна быть 100");

        salesOrder = new SalesOrder(youngUser, "Drill");
        assertEquals(200, salesOrder.getBasePrice(), "Базовая цена должна быть 200");
    }
    @Test
    public void testGetDiscountForYoungUser() {
        SalesOrder salesOrder = new SalesOrder(youngUser, "Hummer");
        assertEquals(1, salesOrder.getDiscount(), "Скидка для молодого пользователя должна быть 1");
    }

    @Test
    public void testGetDiscountForOldUser() {
        SalesOrder salesOrder = new SalesOrder(oldUser, "Hummer");
        assertEquals(0.9, salesOrder.getDiscount(), "Скидка для пожилого пользователя должна быть 0.9");
    }

    @Test
    public void testGetFinalPriceForYoungUser() {
        SalesOrder salesOrder = new SalesOrder(youngUser, "Hummer");
        assertEquals(100, salesOrder.getFinalPrice(), "Итоговая цена для молодого пользователя должна быть 100");
    }

    @Test
    public void testGetFinalPriceForOldUser() {
        SalesOrder salesOrder = new SalesOrder(oldUser, "Hummer");
        assertEquals(90, salesOrder.getFinalPrice(), "Итоговая цена для пожилого пользователя должна быть 90");
    }

    @Test
    public void testDescription() {
        SalesOrder salesOrder = new SalesOrder(youngUser, "Hummer");
        assertEquals("Sale: ", salesOrder.description(), "Описание должно быть 'Sale: '");
    }

    @Test
    public void testInvalidToolName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SalesOrder(youngUser, "InvalidTool");
        });
        assertEquals("Инструмента InvalidTool не существует", exception.getMessage());
    }
}
