import main.CapaDomini.Models.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    @BeforeEach
    void setUp() {
        Document d = new Document();
        assertNotNull(d, "El Document no s'ha creat correctament");
        d= new Document("TEST");
        assertNotNull(d, "El Document no s'ha creat correctament");

    }

    @Test
    void getNom() {
        Document d = new Document("Test");
        assertEquals("Test",d.getNom());
    }

    @Test
    void setNom() {
        Document d = new Document();
        d.setNom("Test");
        assertEquals("Test",d.getNom());
    }

    @Test
    void afegir_full() {

    }

    @Test
    void elimina_full() {

    }

    @Test
    void setData_ultima_mod() {
        Document d = new Document();
        d.setData_ultima_mod(new Date());
        assertEquals(new Date(),d.getData_ultima_mod());
    }

    @Test
    void getFulls() {

    }

    @Test
    void get_full() {

    }

}

