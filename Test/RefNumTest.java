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
        String contingut= "=+#2-3,14,#4-5";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(2, 3),new BigDecimal("5.4"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(4, 5),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        String operacio= "+";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("20.4"),cr.getResultat());
    }

    public void Test_RestaRef() throws Exception {
        String contingut= "=-#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "-";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("6"),cr.getResultat());
    }

    public void Test_MultRef() throws Exception {
        String contingut= "=-#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "-";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("27"),cr.getResultat());
    }

    public void Test_DivRef() throws Exception {
        String contingut= "=-#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "-";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("3"),cr.getResultat());
    }

}
