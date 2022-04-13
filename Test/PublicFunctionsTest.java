import main.CapaDomini.Models.PublicFuntions;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}