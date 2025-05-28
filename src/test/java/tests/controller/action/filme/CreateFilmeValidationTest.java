package tests.controller.action.filme;

import controller.action.filme.CreateFilmeValidation;
import controller.dataBase.FilmeRepository;
import model.Filme;
import model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateFilmeValidationTest {

    private CreateFilmeValidation validation;

    @BeforeEach
    void setUp() {
        validation = new CreateFilmeValidation();
    }

    @Test
    void testTituloVazio() {
        Filme filme = new Filme();
        filme.setTitulo("");
        filme.setGeneros(List.of(new Genero("Ação")));

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testSemGeneros() {
        Filme filme = new Filme();
        filme.setTitulo("Sem Genero");
        filme.setGeneros(List.of()); // Lista vazia

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testFilmeDuplicado() {
        Filme filme = new Filme();
        filme.setTitulo("Duplicado 123");
        filme.setGeneros(List.of(new Genero("Aventura")));

        FilmeRepository.getInstance().add(filme); // Adiciona na base antes

        boolean isValid = validation.isValid(filme);

        assertFalse(isValid);
        assertEquals("Erro! Filme ja existe na base de dados", validation.getErrorMessage());
    }

    @Test
    void testFilmeValido() {
        Filme filme = new Filme();
        filme.setTitulo("Valido 123 " + System.nanoTime());
        filme.setGeneros(List.of(new Genero("Fantasia")));

        boolean isValid = validation.isValid(filme);

        assertTrue(isValid);
        assertNull(validation.getErrorMessage());
    }
}
