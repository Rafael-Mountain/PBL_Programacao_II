package tests.controller.action.filme;

import controller.action.filme.UpdateFilmeValidation;
import model.Filme;
import model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateFilmeValidationTest {

    private UpdateFilmeValidation validation;

    @BeforeEach
    void setUp() {
        validation = new UpdateFilmeValidation();
    }

    @Test
    void testTituloNulo() {
        Filme filme = new Filme();
        filme.setTitulo(null);
        filme.setGeneros(List.of(new Genero("Drama")));

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testTituloVazio() {
        Filme filme = new Filme();
        filme.setTitulo("");
        filme.setGeneros(List.of(new Genero("Comédia")));

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testSemGeneros() {
        Filme filme = new Filme();
        filme.setTitulo("Filme sem genero");
        filme.setGeneros(List.of());

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testFilmeValido() {
        Filme filme = new Filme();
        filme.setTitulo("Filme Atualizado");
        filme.setGeneros(List.of(new Genero("Ação")));

        boolean isValid = validation.isValid(filme);

        assertTrue(isValid);
        assertNull(validation.getErrorMessage());
    }
}
