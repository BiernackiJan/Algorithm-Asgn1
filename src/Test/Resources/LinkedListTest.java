package Resources;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {
    LinkedList<String> names = new LinkedList<>();
    @BeforeEach
    void setUp() {
        names.add("harry");
        names.add("hhhhh");
    }

    @AfterEach
    void tearDown() {
        names=null;
    }

    @Test
    void checkAddCorrectly() {
        assertEquals("harry", names.get(0));
        names.add("mary");
        assertEquals("mary", names.get(2));
    }

    @Test
    void get() {

    }

    @Test
    void listAll() {
    }

    @Test
    void addNodes() {
    }

    @Test
    void numNodes() {
    }
}
