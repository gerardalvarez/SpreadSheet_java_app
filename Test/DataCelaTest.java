import main.CapaDomini.Models.DataCela;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataCelaTest {

    @Test
    public void changeText(){
        DataCela c = new DataCela(new AbstractMap.SimpleEntry<>(1, 1),"11/02/2008");
        c.changeToText();
        assertEquals("11 de febrer del 2008", c.getContingut());
        /*DataCela b = new DataCela(new AbstractMap.SimpleEntry<>(1, 1),"09 de agost del 2018");
        b.changeToText();
        assertEquals("09 de agost del 2018", b.getContingut());*/
    }

    @Test
    public void changeData(){
        DataCela c = new DataCela(new AbstractMap.SimpleEntry<>(1, 1),"11 de febrer del 2008");
        c.changeToDate();
        assertEquals("11/02/2008", c.getContingut());
        DataCela b = new DataCela(new AbstractMap.SimpleEntry<>(1, 1),"12/03/2028");
        b.changeToDate();
        assertEquals("12/03/2028", b.getContingut());
    }

    @Test
    public void getElements(){
        DataCela c = new DataCela(new AbstractMap.SimpleEntry<>(1, 1),"11 de febrer del 2008");
        assertEquals("11", c.getDia());
        assertEquals("FEBRUARY", c.getMes());
        assertEquals("2008", c.getAny());
        assertEquals("MONDAY" , c.getWeekDay());
    }
}