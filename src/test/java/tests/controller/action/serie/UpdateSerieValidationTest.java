package tests.controller.action.serie;

import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateSerieValidationTest {

    private UpdateSerieValidation validation;

    @BeforeEach
    void setUp() {
        validation = new UpdateSerieValidation();
    }

    @Test
    void testTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo(""); // Título vazio
        serie.setGeneros(List.of(new Genero("Ação")));

        boolean isValid = validation.isValid(serie);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testTituloNulo() {
        Serie serie = new Serie();
        serie.setTitulo(null); // Título nulo
        serie.setGeneros(List.of(new Genero("Comédia")));

        boolean isValid = validation.isValid(serie);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testGenerosVazios() {
        Serie serie = new Serie();
        serie.setTitulo("Série Teste");
        serie.setGeneros(List.of()); // Nenhum gênero

        boolean isValid = validation.isValid(serie);

        assertFalse(isValid);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testSerieValida() {
        Serie serie = new Serie();
        serie.setTitulo("Série Válida");
        serie.setGeneros(List.of(new Genero("Drama")));

        boolean isValid = validation.isValid(serie);

        assertTrue(isValid);
        assertNull(validation.getErrorMessage());
    }
}
