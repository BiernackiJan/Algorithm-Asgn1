package Resources;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LinkedListTest {
    LinkedList<String> names = new LinkedList<>();
    LinkedList<String> months = new LinkedList<>();
    @BeforeEach
    void setUp() {
        names.add("harry");
        names.add("hhhhh");
        names.add("mary");
    }

    @AfterEach
    void tearDown() {
        names = null;
        months = null;
    }

    @Test
    void checkAddCorrectly() {
        assertEquals("harry", names.get(0));
        names.add("John");
        assertEquals("John", names.get(3));
        assertNull(names.get(4));
    }

    @Test
    void checkGetCorrectly() {
        assertEquals(null, names.get(3));
        assertEquals(names.getHead().getData(),names.get(0));
        assertEquals("hhhhh", names.get(1));
    }

    @Test
    void checkListAll() {
        assertEquals("There are no items", months.listAll());
        assertEquals("" + names.getHead().getData() + names.get(1) + names.get(2), names.listAll());
    }

    @Test
    void checkAddNode() {
        assertEquals(3,names.numNodes());
        names.add("George");
        assertEquals(4,names.numNodes());
    }


    @Test
    void checkDeleteNode() {
        assertEquals("harry", names.getHead().getData());
        names.deleteNode(0);
        assertEquals("hhhhh",names.getHead().getData());

        months.add("Head Node");
        assertEquals("Head Node", months.getHead().getData());
        months.deleteNode(0);
        assertNull(months.getHead());

        names.add("George");
        assertEquals("mary",names.get(1));
        names.deleteNode(1);
        assertEquals("George",names.get(1));
        assertNull(names.get(2));
    }

    @Test
    void checkDeleteAll(){
        assertEquals("harryhhhhhmary", names.listAll());
        assertEquals("harry", names.getHead().getData());
        names.delAll();
        assertNull(names.getHead());
        assertEquals("There are no items",names.listAll());
    }
}
