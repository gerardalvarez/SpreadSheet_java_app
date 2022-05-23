import main.CapaDomini.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class Bloc_celesTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void ordena_A_Z() throws Exception {

        // (0,0)1 c   (0,1)b c3      (0,0)1 c2   (0,1)a c5
        // (1,0)2 c1  (1,1)c c4  to  (1,0)1 c    (1,1)b c3
        // (2,0)1 c2  (2,1)a c5      (2,0)2 c1   (2,1)c c4


        Full f=new Full(2,3);
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 0), "1");

        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 1), "b");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 1), "c");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 1), "a");

        for (Integer g=0; g < f.getNum_Files(); ++g) { //PRINT
            for (Integer j = 0; j < f.getNum_Columnes(); ++j) System.out.print(f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)).getId() + " " + f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)) .getResultatFinal()+ " ");
            System.out.println();
        }

        ArrayList<Integer> cols = new ArrayList<>();
        cols.add(0);
        cols.add(1);
        f.ordena_bloc(new AbstractMap.SimpleEntry<>(0, 0),new AbstractMap.SimpleEntry<>(2, 1),cols,"Major-menor");
        Bloc_celes bloc=new Bloc_celes();


        for (Integer g=0; g < f.getNum_Files(); ++g) { //PRINT
            for (Integer j = 0; j < f.getNum_Columnes(); ++j) System.out.print(f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)).getId() + " " + f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)) .getResultatFinal()+ " ");
            System.out.println();
        }

        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(0,0)).getResultatFinal(),"2.00");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(1,0)).getResultatFinal(),"1.00");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(2,0)).getResultatFinal(),"1.00");

        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(0,1)).getResultatFinal(),"c");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(1,1)).getResultatFinal(),"b");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(2,1)).getResultatFinal(),"a");


    }

    @Test
    void ordena_Z_A() throws Exception {

        // (0,0)1 c   (0,1)b c3      (0,0)1 c2   (0,1)a c5
        // (1,0)2 c1  (1,1)c c4  to  (1,0)1 c    (1,1)b c3
        // (2,0)1 c2  (2,1)a c5      (2,0)2 c1   (2,1)c c4


        Full f=new Full(2,3);
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 0), "1");

        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 1), "b");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 1), "c");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 1), "a");

        for (Integer g=0; g < f.getNum_Files(); ++g) { //PRINT
            for (Integer j = 0; j < f.getNum_Columnes(); ++j) System.out.print(f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)).getId() + " " + f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)) .getResultatFinal()+ " ");
            System.out.println();
        }

        ArrayList<Integer> cols = new ArrayList<>();
        cols.add(0);
        cols.add(1);
        f.ordena_bloc(new AbstractMap.SimpleEntry<>(0, 0),new AbstractMap.SimpleEntry<>(2, 1),cols,"Menor-major");
        Bloc_celes bloc=new Bloc_celes();


        for (Integer g=0; g < f.getNum_Files(); ++g) { //PRINT
            for (Integer j = 0; j < f.getNum_Columnes(); ++j) System.out.print(f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)).getId() + " " + f.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j)) .getResultatFinal()+ " ");
            System.out.println();
        }

        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(0,0)).getResultatFinal(),"1.00");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(1,0)).getResultatFinal(),"1.00");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(2,0)).getResultatFinal(),"2.00");

        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(0,1)).getResultatFinal(),"a");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(1,1)).getResultatFinal(),"b");
        assertEquals(f.getCeles().get(new AbstractMap.SimpleEntry<Integer,Integer>(2,1)).getResultatFinal(),"c");

    }

    @Test
    void calculaMitjana() {

        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1.3");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1.4");
        list.add(c);
        list.add(c1);
        list.add(c2);
        assertTrue(bloc.calculaMitjana(list)==(1.5666666666666664));
    }

    @Test
    void calculaMediana() {
        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1");
        Numero c3 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "7");
        list.add(c);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        //La mediana de 1 2 1 7 és 1.5
        assertTrue(bloc.calculaMediana(list)==(1.5));

    }

    @Test
    void calculaModa() {

        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1");
        Numero c3 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "7");
        list.add(c);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        //La moda de 1 2 1 7 és 1
         assertTrue(bloc.calculaModa(list)==(1.0));
    }

    @Test
    void calculaVariança() {
        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1");
        Numero c3 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "7");
        list.add(c);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        assertTrue(bloc.calculaVariança(list)==(8.249999999999998));
    }

    @Test
    void calculaDesviació() {
        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1");
        Numero c3 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "7");
        list.add(c);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        assertTrue(bloc.calculaDesviació(list)==(2.872281323269014));
    }
    @Test
    void maxim() {
        Bloc_celes bloc=new Bloc_celes();

        ArrayList<Numero> list = new ArrayList<>();
        Numero c = new Numero(new AbstractMap.SimpleEntry<>(0, 0), "1");
        Numero c1 = new Numero(new AbstractMap.SimpleEntry<>(1, 0), "2");
        Numero c2 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "1");
        Numero c3 = new Numero(new AbstractMap.SimpleEntry<>(2, 0), "7");
        list.add(c);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        assertTrue(bloc.maxim(list)==(7.0));
    }

    @Test
    void remplaçar_majuscules() {

        Bloc_celes bloc=new Bloc_celes();

        ArrayList<TextCela> list = new ArrayList<>();
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(0, 1), "baaa");
        TextCela c1 = new TextCela(new AbstractMap.SimpleEntry<>(1, 1), "csss");
        TextCela c2 = new TextCela(new AbstractMap.SimpleEntry<>(2, 1), "aaaa");
        list.add(c);
        list.add(c1);
        list.add(c2);
        bloc.remplaçar_majuscules(list);
        assertTrue(c.getResultatFinal().equals("BAAA"));
        assertTrue(c1.getResultatFinal().equals("CSSS"));
        assertTrue(c2.getResultatFinal().equals("AAAA"));
    }

    @Test
    void remplaçar_minuscules() {

        Bloc_celes bloc=new Bloc_celes();

        ArrayList<TextCela> list = new ArrayList<>();
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(0, 1), "baaa");
        TextCela c1 = new TextCela(new AbstractMap.SimpleEntry<>(1, 1), "cSSss");
        TextCela c2 = new TextCela(new AbstractMap.SimpleEntry<>(2, 1), "Aaaa");
        list.add(c);
        list.add(c1);
        list.add(c2);
        bloc.remplaçar_minuscules(list);
        assertTrue(c.getResultatFinal().equals("baaa"));
        assertTrue(c1.getResultatFinal().equals("cssss"));
        assertTrue(c2.getResultatFinal().equals("aaaa"));
    }

    @Test
    void buscar_y_remplazar() {

        /*Bloc_celes bloc=new Bloc_celes();
        ArrayList<TextCela> list = new ArrayList<>();
        TextCela c = new TextCela(new AbstractMap.SimpleEntry<>(0, 1), "Carloz");
        TextCela c1 = new TextCela(new AbstractMap.SimpleEntry<>(1, 1), "zzzAaasz");
        TextCela c2 = new TextCela(new AbstractMap.SimpleEntry<>(2, 1), "Aaaa");
        list.add(c);
        list.add(c1);
        list.add(c2);
        bloc.buscar_y_remplazar(list,"z","s");

        assertTrue(c.getResultatFinal().equals("Carlos"));
        assertTrue(c1.getResultatFinal().equals("sssAaass"));
        assertTrue(c2.getResultatFinal().equals("Aaaa"));*/
    }
}


