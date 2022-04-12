import main.CapaDomini.Models.TextCela;
import org.junit.Test;
import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.*;

public class TextTest {

    @Test
    public void BuscarElement(){
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(1, 1),"Hello World");
        assertTrue(c.buscarElement("ello"));
        assertFalse(c.buscarElement("GOAT"));

    }

    @Test
    public void RemplElement(){
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(1, 1),"Hello World");
        c.remplacarElement("ello", "ola");
        assertEquals("Hola World", c.getContingut());
        TextCela d = new TextCela(new AbstractMap.SimpleEntry<>(1, 1),"Hello World");
        d.remplacarElement("ola", "GOAT");
        assertEquals("Hello World", c.getContingut());
    }

    @Test
    public void MayusAndMinus(){
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(1, 1),"Hello World");
        c.AllMayus();
        assertEquals("HELLO WORLD", c.getContingut());
        assertNotEquals("HELLO WORLd", c.getContingut());
        c.AllMinus();
        assertEquals("hello world", c.getContingut());
    }
}