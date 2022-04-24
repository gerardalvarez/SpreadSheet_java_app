import main.CapaDomini.Models.Cela;
import main.CapaDomini.Models.Full;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullTest {
    @Test
    public void Test_afegir_fila() throws Exception {
        Full f= new Full( 20,20);
        f.Afegir_Fila(10);
        assertEquals(21, f.getNum_Files());
    }
    @Test
    public void Test_eliminar_fila() throws Exception {
        Full f= new Full( 13,8);
        f.Eliminar_Fila(8);
        assertEquals(7, f.getNum_Files());
    }
    @Test
    public void Test_afegir_col() throws Exception {
        Full f= new Full( 18,20);
        f.Afegir_Columna(18);
        assertEquals(19, f.getNum_Columnes());
    }
    @Test
    public void Test_eliminar_col() throws Exception {
        Full f= new Full( 13,8);
        f.Eliminar_Columna(3);
        assertEquals(12, f.getNum_Columnes());
    }
    @Test
    public void Esborra_cont() throws Exception {
        Full f= new Full( 13,8);
        AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(4, 5);
        ArrayList<Cela> c= new ArrayList<Cela>();
        c.add(f.Consultar_cela(idc));
        f.Esborrar_Celes(c);
        assertEquals("", f.Consultar_cela(idc).getResultatFinal());
    }
    @Test
    public void ModTipusNum() throws Exception {
        Full f= new Full( 13,8);
        AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(4, 5);
        f.getCeles().get(idc).setResultat("2");
        f.Modifica_Tipus_Numeric(idc);
        assertEquals("numeric", f.getCeles().get(idc).getType());
    }
    @Test
    public void ModTipusText() throws Exception {
        Full f= new Full( 13,8);
        AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(4, 5);
        f.Modifica_Tipus_Text(idc);
        assertEquals("text", f.getCeles().get(idc).getType());
    }
    @Test
    public void ModTipusData() throws Exception {
        Full f= new Full( 13,8);
        AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(4, 5);
        f.getCeles().get(idc).setResultat("11 de febrer del 2008");
        f.Modifica_Tipus_Data(idc);
        assertEquals("data", f.getCeles().get(idc).getType());
    }
}
