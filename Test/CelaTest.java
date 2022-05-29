/*import main.CapaDomini.Models.Cela;
import main.CapaDomini.Models.CelaAntiga;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
//CELA ANTIGA ES LA CELA SENSE ABSTRACT QUE ES VA UTILITZAR PER FER EL TESTING
public class CelaTest {

    @Test
    public void TypeNumber(){
        CelaAntiga c = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"4.15");
        assertEquals("numeric", c.calculaTipus());
    }
    @Test
    public void TypeText(){
        CelaAntiga c = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"4.15.15");
        assertEquals("text", c.calculaTipus());
        assertEquals("text", c.getType());
    }

    @Test
    public void TypeDate(){
        CelaAntiga c = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"05/04/2001");
        assertEquals("date", c.calculaTipus());
        CelaAntiga d = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"30 de gener del 2001");
        assertEquals("date", d.calculaTipus());
        CelaAntiga e = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"30 de febrer del 2001");
        assertEquals("text", e.calculaTipus());
        CelaAntiga f = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1),"30 de febrer del 2001 ");
        assertEquals("text", f.calculaTipus());
        CelaAntiga g = new CelaAntiga(new AbstractMap.SimpleEntry<>(1, 1)," 30 de febrer del 2001 ");
        assertEquals("text", g.calculaTipus());
    }
    @Test
    public void PrivateTests(){
        String len = "hola";
        int num = len.length();
        assertEquals(4,num);
        assertEquals("a", len.substring(3));
        String date = "30 de gener del 2001";
        String dd = date.substring(0,2);
        assertEquals("30", dd);
        String yyyy = date.substring(date.length()-4);
        assertEquals("2001", yyyy);
        String monthText = date.substring(2,date.length()-4);
        assertEquals(" de gener del ", monthText);
    }





}*/