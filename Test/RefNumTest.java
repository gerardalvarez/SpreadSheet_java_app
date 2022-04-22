import main.CapaDomini.Models.CelaRefNum;

import main.CapaDomini.Models.Numero;
import main.CapaDomini.Models.Tipus_Numero;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefNumTest {
    @Test
    public void Test_SumaRef() throws Exception {
        String contingut= "=+#23,14";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(2, 3),new BigDecimal("5.4"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "+";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(19.4, cr.getResultatt());
    }

}
