package collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;



public class creatingCollectionsTest {

    private creatingCollections example;
    private ArrayList<String> list;

    @BeforeEach
    void setUp() {
        example = new creatingCollections();
        list = example.createAndAddElements();
        ArrayList<String> createAndAddElements;
    }


    @Test
    void testCreateAndAddElements() {
        assertEquals(3, list.size());
        assertTrue(list.contains("Apple"));
        assertTrue(list.contains("Banana"));
        assertTrue(list.contains("Cherry"));
    }

    @Test
    void testAddElementAtIndex() {
        example.addElementAtIndex(list, 1, "Orange");
        assertEquals(4, list.size());
        assertEquals("Orange", list.get(1));
    }

    @Test
    void testGetElementByIndex() {
        String element = example.getElementByIndex(list, 2);
        assertEquals("Cherry", element);
    }

    @Test
    void testSetElementAtIndex() {
        example.setElementAtIndex(list, 1, "Grapes");
        assertEquals("Grapes", list.get(1));
    }

    @Test
    void testRemoveElementByValue() {
        example.removeElementByValue(list, "Banana");
        assertEquals(2, list.size());
        assertFalse(list.contains("Banana"));
    }

    @Test
    void testRemoveElementByIndex() {
        example.removeElementByIndex(list, 0);
        assertEquals(2, list.size());
        assertFalse(list.contains("Apple"));
    }

    @Test
    void testContainsElement() {
        assertTrue(example.containsElement(list, "Banana"));
        assertFalse(example.containsElement(list, "Mango"));
    }

    @Test
    void testGetSize() {
        assertEquals(3, example.getSize(list));
    }

    @Test
    void testClearList() {
        example.clearList(list);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
}

