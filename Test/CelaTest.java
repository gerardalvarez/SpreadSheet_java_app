import main.CapaDomini.Models.Cela;
import org.junit.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CelaTest {

    @Test
    public void TypeNumber(){
        Cela c = new Cela(new AbstractMap.SimpleEntry<>(1, 1),"4.15");
        assertEquals("numeric", c.calculaTipus());
    }
    @Test
    public void TypeText(){
        Cela c = new Cela(new AbstractMap.SimpleEntry<>(1, 1),"4.15.15");
        assertEquals("text", c.calculaTipus());
    }

    @Test
    public void TypeDate(){
        Cela c = new Cela(new AbstractMap.SimpleEntry<>(1, 1),"05/04/2001");
        assertEquals("date", c.calculaTipus());
    }





}