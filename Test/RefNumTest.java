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
        String contingut= "=SUM#2-3,14,#4-5";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(2, 3),new BigDecimal("5.4"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(4, 5),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        String operacio= "SUM";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("20.4"),cr.getResultat());
    }
    @Test
    public void Test_RestaRef() throws Exception {
        String contingut= "=RES#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "RES";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("6"),cr.getResultat());
    }
    @Test
    public void Test_MultRef() throws Exception {
        String contingut= "=MUL#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "MUL";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("27"),cr.getResultat());
    }
    @Test
    public void Test_DivRef() throws Exception {
        String contingut= "=DIV#8-9,3";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        String operacio= "DIV";
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("3"),cr.getResultat());
    }
    @Test
    public void Test_Copia() throws Exception {
        String contingut= "=COP#8-9";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("9"),true,2, Tipus_Numero.numero);
        String operacio= "COP";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        System.out.println(cr.getResultatFinal());
        System.out.println(n1.getResultatFinal());
        assertEquals(new BigDecimal("9"),cr.getResultat());
    }

    @Test
    public void Test_Average() throws Exception {
        String contingut= "=AVG#8-9,#3-4,#4-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1.3"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1.4"),true,2, Tipus_Numero.numero);
        String operacio= "AVG";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("1.5666666666666664"),cr.getResultat());
    }

    @Test
    public void Test_Mediana() throws Exception {
        String contingut= "=MED#8-9,#3-4,#4-6,#5-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(5, 6),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        String operacio= "MED";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("1.5"),cr.getResultat());
    }

    @Test
    public void Test_Variança() throws Exception {
        String contingut= "=VAR#8-9,#3-4,#4-6,#5-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(5, 6),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        String operacio= "VAR";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("8.249999999999998"),cr.getResultat());
    }

    @Test
    public void Test_Moda() throws Exception {
        String contingut= "=MOD#8-9,#3-4,#4-6,#5-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(5, 6),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        String operacio= "MOD";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("1.0"),cr.getResultat());
    }

    @Test
    public void Test_Max() throws Exception {
        String contingut= "=MAX#8-9,#3-4,#4-6,#5-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(5, 6),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        String operacio= "MAX";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("7.0"),cr.getResultat());
    }

    @Test
    public void Test_Desviacio() throws Exception {
        String contingut= "=DES#8-9,#3-4,#4-6,#5-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(8, 9),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(3, 4),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(4, 6),new BigDecimal("1"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(5, 6),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        String operacio= "DES";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("2.872281323269014"),cr.getResultat());
    }

    @Test
    public void Test_Pearson() throws Exception {
        String contingut= "=PER#1-2,#1-3,#1-4;#2-4,#2-5,#2-6";
        Numero n1= new Numero(new AbstractMap.SimpleEntry<>(1, 2),new BigDecimal("4"),true,2, Tipus_Numero.numero);
        Numero n2= new Numero(new AbstractMap.SimpleEntry<>(1, 3),new BigDecimal("7"),true,2, Tipus_Numero.numero);
        Numero n3= new Numero(new AbstractMap.SimpleEntry<>(1, 4),new BigDecimal("3"),true,2, Tipus_Numero.numero);
        Numero n4= new Numero(new AbstractMap.SimpleEntry<>(2, 4),new BigDecimal("8"),true,2, Tipus_Numero.numero);
        Numero n5= new Numero(new AbstractMap.SimpleEntry<>(2, 5),new BigDecimal("10"),true,2, Tipus_Numero.numero);
        Numero n6= new Numero(new AbstractMap.SimpleEntry<>(2, 6),new BigDecimal("2"),true,2, Tipus_Numero.numero);
        String operacio= "PER";
        ArrayList<Numero> operadors= new ArrayList<Numero>();
        operadors.add(n1);
        operadors.add(n2);
        operadors.add(n3);
        operadors.add(n4);
        operadors.add(n5);
        operadors.add(n6);
        CelaRefNum cr= new CelaRefNum(new AbstractMap.SimpleEntry<>(1, 1),true,2, Tipus_Numero.numero, contingut, operadors, operacio);
        assertEquals(new BigDecimal("1.1384615384615382"),cr.getResultat());
    }

}
