import main.CapaDomini.Models.Numero;
import main.CapaDomini.Models.Tipus_Numero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
    public void ArrelTest() {
        Numero t = new Numero("4", true, 2, Tipus_Numero.numero);
        t.arrel(2.0);
        assertEquals(BigDecimal.valueOf(2),t.getResultat());
    }

    @Test
    public void AbsTest() {
        Numero t = new Numero("-4", true, 2, Tipus_Numero.numero);
        t.valor_absolut();
        assertEquals(BigDecimal.valueOf(4),t.getResultat());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ConversioTesting.csv", numLinesToSkip = 1)
    public void ConversioTest(String contingut, String tipus, String convertit, String expected) {
        Numero t = new Numero(contingut, true, 2, Tipus_Numero.valueOf(tipus));
        t.conversio(Tipus_Numero.valueOf(convertit));
        t.setDecimals();
        assertEquals(new BigDecimal(expected),t.getResultat());
    }

}
