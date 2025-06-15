package tests.model;

import com.mountain_vd.model.Genero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneroTest {

    @Test
    void testConstrutorEGetNome() {
        Genero genero = new Genero("Ação");
        assertEquals("Ação", genero.getNome());
    }

    @Test
    void testToStringRetornaNome() {
        Genero genero = new Genero("Comédia");
        assertEquals("Comédia", genero.toString());
    }

    @Test
    void testSetAndGetId() {
        Genero genero = new Genero("Drama");
        genero.setId(42);
        assertEquals(42, genero.getId());
    }
}
