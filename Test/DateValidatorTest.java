import main.CapaDomini.Models.DataValidator;
import main.CapaDomini.Models.DateValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DateValidatorTest {

    @Test
    public void Valid(){
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        assertTrue(validator.isValid("28/02/2019"));
        assertFalse(validator.isValid("30/02/2019"));
    }



}