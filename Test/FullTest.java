import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Cela;
import main.CapaDomini.Models.Full;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FullTest {
    @Test
    public void Test_afegir_fila() throws Exception {
        Full f= new Full(4,4);
        for(int i=0; i<f.getNum_Columnes();++i) f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2,i),"aa");
        f.Afegir_Fila(2);
        assertNull(f.getCeles());
    }
    @Test
    public void Test_eliminar_fila() throws Exception {
        Full f= new Full(4,4);
        for(int i=0; i<f.getNum_Columnes();++i) f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2,i),"aa");
        f.Eliminar_Fila(2);
        assertNull(f.getCeles());
    }
    @Test
    public void Test_afegir_col() throws Exception {
        Full f= new Full(4,4);
        for(int i=0; i<f.getNum_Files();++i) f.Modifica_Cela(new AbstractMap.SimpleEntry<>(i,3),"aa");
        f.Eliminar_Columna(2);
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
        f.getCeles().get(idc).setResultatFinal("2");
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
        f.getCeles().get(idc).setResultatFinal("11 de febrer del 2008");
        f.Modifica_Tipus_Data(idc);
        assertEquals("data", f.getCeles().get(idc).getType());
    }
    @Test
    public void undomodifica() throws Exception {
        CtrlDomini d= new CtrlDomini();
        d.CrearDocument("1");
        d.CrearFull("1","1f",3,3);
        d.AfegirCol("1", "1f", 2);
        d.Undo("1","1f");
        assertEquals("data", d.Mostrar("1","1f"));
    }
    @Test
    public void undocalcula() throws Exception {
        CtrlDomini d= new CtrlDomini();
        d.CrearDocument("1");
        d.CrearFull("1","1f",4,4);
        AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(0, 0);
        AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<>(1, 1);
        AbstractMap.SimpleEntry<Integer, Integer> idcfin = new AbstractMap.SimpleEntry<>(3, 3);
        d.modificarContingutCela("1","1f", idc, "2");
        d.modificarContingutCela("1","1f", new AbstractMap.SimpleEntry<>(0, 1), "3");
        d.modificarContingutCela("1","1f", new AbstractMap.SimpleEntry<>(1, 0), "10");
        d.modificarContingutCela("1","1f", idc2, "8");
        d.CalculaDesviacio("1","1f", idc, idc2, idcfin);
        //d.Undo("1","1f");
        assertEquals("data", d.Mostrar("1","1f"));
    }
}
