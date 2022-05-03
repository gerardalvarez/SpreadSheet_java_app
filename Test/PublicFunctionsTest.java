import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Full;
import main.CapaDomini.Models.PublicFuntions;
import org.junit.jupiter.api.Test;

import static main.CapaDomini.Models.PublicFuntions.esRef;
import static org.junit.jupiter.api.Assertions.*;

public class PublicFunctionsTest {

    @Test
    public void toData(){
        String a = PublicFuntions.monthToData(" de març del ");
        assertEquals("03", a);
        String b = PublicFuntions.monthToData(" de març del");
        assertEquals("null", b);
    }

    @Test
    public void toText(){
        String a = PublicFuntions.monthToText("10");
        assertEquals(" de octubre del ", a);
        String b = PublicFuntions.monthToText("13");
        assertEquals("null", b);
    }

    @Test
    public void esReferencia(){
        Boolean b = esRef("=#23_01", 50 , 50);
        assertTrue(b);
    }

    @Test
    public void reasCSVTest() throws Exception {
        Full f = PublicFuntions.readCsv();
        System.out.println(f.Mostrar());
    }
}