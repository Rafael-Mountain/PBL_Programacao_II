package tests.controller.action.serie;

import controller.action.serie.CreateSerieValidation;
import model.Serie;
import model.Genero;
import controller.dataBase.SerieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSerieValidationTest {

    private CreateSerieValidation validation;

    @BeforeEach
    void setUp() {
        validation = new CreateSerieValidation();

        // Limpa o repositório manualmente antes de cada teste
        SerieRepository.getInstance().getItems().clear();  // Limpeza manual da lista de itens
    }

    @Test
    void testTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo("");  // Título vazio
        serie.setGeneros(List.of(new Genero("Aventura")));

        boolean isValid = validation.isValid(serie);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testGeneroVazio() {
        Serie serie = new Serie();
        serie.setTitulo("Série Sem Gênero");
        serie.setGeneros(List.of());  // Gêneros vazios

        boolean isValid = validation.isValid(serie);

        assertFalse(isValid);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testSerieJaExistenteNoRepositório() {
        // Adiciona uma série no repositório
        Serie serieExistente = new Serie();
        serieExistente.setTitulo("Série Existente");
        serieExistente.setGeneros(List.of(new Genero("Comédia")));
        SerieRepository.getInstance().add(serieExistente);

        // Tenta adicionar uma série com o mesmo título
        Serie serieDuplicada = new Serie();
        serieDuplicada.setTitulo("Série Existente");
        serieDuplicada.setGeneros(List.of(new Genero("Drama")));

        boolean isValid = validation.isValid(serieDuplicada);

        assertFalse(isValid);
        assertEquals("Erro! Serie ja existe na base de dados", validation.getErrorMessage());
    }

    @Test
    void testSerieValida() {
        Serie serie = new Serie();
        serie.setTitulo("Nova Série");
        serie.setGeneros(List.of(new Genero("Aventura")));

        boolean isValid = validation.isValid(serie);

        assertTrue(isValid);
    }
}
