import main.CapaDomini.Models.Numero;
import main.CapaDomini.Models.Tipus_Numero;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumeroTest {

    @Test
    public void IncrementarTest(){
        Numero t = new Numero("5.4",true,2, Tipus_Numero.numero);
        t.incrementar();
        assertEquals(BigDecimal.valueOf(6.4), t.getResultat());
    }

    @Test
    public void ReduirTest(){
        Numero t = new Numero("5.4",true,2, Tipus_Numero.numero);
        t.reduir();
        assertEquals(BigDecimal.valueOf(4.4), t.getResultat());
    }

    @Test
    public void PotenciaTest() {
        Numero t = new Numero("2", true, 2, Tipus_Numero.numero);
        t.potencia(2);
        assertEquals(BigDecimal.valueOf(4),t.getResultat());
    }

    @Test
    public void PotenciaTresDecimalsTest() {
        Numero t = new Numero("2.563", true, 2, Tipus_Numero.numero);
        t.potencia(2);
        t.setDecimals();
        assertEquals(BigDecimal.valueOf(6.57),t.getResultat());
    }

    @Test
    public void PotenciaComplicada() {
        Numero t = new Numero("-2.565", true, 3, Tipus_Numero.numero);
        t.potencia(6);
        t.setDecimals();
        assertEquals(BigDecimal.valueOf(284.790).setScale(3, RoundingMode.HALF_UP),t.getResultat());
    }

    @Test
    public void ConversioTest() {
        Numero t = new Numero("56.7891", true, 0, Tipus_Numero.m);
        t.conversio(Tipus_Numero.km);
        assertEquals(BigDecimal.valueOf(0.0567891),t.getResultat());
    }

}
