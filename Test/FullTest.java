import main.CapaDomini.Models.Full;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullTest {
    @Test
    public void Test_afegir_fila(){
        Full f= new Full(1, 20,20);
        f.Afegir_Fila(10);
        assertEquals(21, f.getNum_Files());
    }
    @Test
    public void Test_eliminar_fila(){
        Full f= new Full(1, 13,8);
        f.Eliminar_Fila(8);
        assertEquals(7, f.getNum_Files());
    }
    @Test
    public void Test_afegir_col(){
        Full f= new Full(1, 18,20);
        f.Afegir_Columna(18);
        assertEquals(19, f.getNum_Columnes());
    }
    @Test
    public void Test_eliminar_col(){
        Full f= new Full(1, 13,8);
        f.Eliminar_Columna(3);
        assertEquals(12, f.getNum_Columnes());
    }
}
