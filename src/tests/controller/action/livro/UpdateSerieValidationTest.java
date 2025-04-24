package tests.controller.action.livro;

import controller.action.serie.UpdateSerieValidation;
import model.Genero;
import model.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateSerieValidationTest {

    private UpdateSerieValidation validation;

    @BeforeEach
    void setUp() {
        validation = new UpdateSerieValidation();
    }

    @Test
    void testTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo(""); // título vazio
        serie.setGeneros(List.of(new Genero("Comédia")));

        boolean result = validation.isValid(serie);

        assertFalse(result);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testGenerosVazio() {
        Serie serie = new Serie();
        serie.setTitulo("The Office");
        serie.setGeneros(List.of()); // lista de gêneros vazia

        boolean result = validation.isValid(serie);

        assertFalse(result);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testSerieValida() {
        Serie serie = new Serie();
        serie.setTitulo("Stranger Things");
        serie.setGeneros(List.of(new Genero("Ficção")));

        boolean result = validation.isValid(serie);

        assertTrue(result);
        assertNull(validation.getErrorMessage());
    }
}
