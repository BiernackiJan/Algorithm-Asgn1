package models;

import Resources.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.asgn.algorithmasgn1.CaseController.list;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DisplayCaseTest {
    LinkedList<DisplayCase> cases = new LinkedList<>();


    @BeforeEach
    void setUp() {
        cases.add(new DisplayCase("A","1","mounted","lit"));
        cases.add(new DisplayCase("B","2","free-standing", "un-lit"));
        DisplayCase dc = (DisplayCase) cases.get(0);

        dc.displayTrays.add(new DisplayTray("F","9","green", "12","12"));
        DisplayTray dt = (DisplayTray) dc.displayTrays.get(0);

        dt.items.add( new Items("handmade","ring","male",50, "url"));
        Items items = (Items) dt.items.get(0);

        int iPrice = items.getrPrice();
        dt.priceUp(iPrice);
    }

    @AfterEach
    void tearDown(){
        cases.delAll();
    }

    @Test
    void caseValueTest() {
        assertEquals(0, ((DisplayCase) cases.get(0)).casePrice);
        ((DisplayCase) cases.get(0)).caseValue();
        assertEquals(50, ((DisplayCase) cases.get(0)).casePrice);
    }

    @Test
    void testIdentifier(){
        assertEquals("A1", ((DisplayCase) cases.get(0)).identifier());
    }

    @Test
    void testSetLetter(){
        assertEquals("A", ((DisplayCase) cases.get(0)).getLetter());
        ((DisplayCase) cases.get(0)).setLetter("C");
        assertEquals("C", ((DisplayCase) cases.get(0)).getLetter());
    }
}
