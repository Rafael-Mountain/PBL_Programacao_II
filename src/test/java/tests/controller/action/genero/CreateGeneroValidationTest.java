package tests.controller.action.genero;

import com.mountain_vd.controller.action.genero.CreateGeneroValidation;
import com.mountain_vd.model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mountain_vd.controller.dataBase.GeneroRepository;

import static org.junit.jupiter.api.Assertions.*;

public class CreateGeneroValidationTest {

    private CreateGeneroValidation validation;

    @BeforeEach
    public void setUp() {
        validation = new CreateGeneroValidation();
        GeneroRepository.getInstance().getItems().clear(); // limpeza manual
    }

    @Test
    public void testNomeVazio() {
        Genero genero = new Genero("");
        assertFalse(validation.isValid(genero));
        assertEquals("Erro! Nome do gênero não pode ser vazio", validation.getErrorMessage());
    }

    @Test
    public void testNomeCurto() {
        Genero genero = new Genero("An");
        assertFalse(validation.isValid(genero));
        assertEquals("Erro! Nome do gênero deve ter pelo menos 3 caracteres", validation.getErrorMessage());
    }

    @Test
    public void testNomeLongo() {
        Genero genero = new Genero("A".repeat(51));
        assertFalse(validation.isValid(genero));
        assertEquals("Erro! Nome do gênero não pode ter mais de 50 caracteres", validation.getErrorMessage());
    }

    @Test
    public void testGeneroDuplicado() {
        Genero generoExistente = new Genero("Aventura");
        GeneroRepository.getInstance().add(generoExistente);

        Genero generoNovo = new Genero("Aventura");
        assertFalse(validation.isValid(generoNovo));
        assertEquals("Erro! Gênero já existe na base de dados", validation.getErrorMessage());
    }

    @Test
    public void testGeneroValido() {
        Genero genero = new Genero("Terror");
        assertTrue(validation.isValid(genero));
        assertNull(validation.getErrorMessage());
    }
}
