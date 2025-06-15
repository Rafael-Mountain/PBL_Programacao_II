package tests.controller.action.livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateSerieActionTest {

    private UpdateSerieAction action;
    private SerieRepository repository;

    @BeforeEach
    void setUp() {
        action = new UpdateSerieAction(new UpdateSerieValidation());
        repository = SerieRepository.getInstance();
        repository.getItems().clear();
    }

    @Test
    void testSerieComTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo("");
        serie.setGeneros(List.of(new Genero("Ação")));

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! título vazio", result.getMessage());
    }

    @Test
    void testSerieComGeneroVazio() {
        Serie serie = new Serie();
        serie.setTitulo("Breaking Bad");
        serie.setGeneros(List.of()); // Sem gêneros

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um gênero", result.getMessage());
    }

    @Test
    void testUpdateSerieComSucesso() {
        Serie serie = new Serie();
        serie.setTitulo("Dark");
        serie.setGeneros(List.of(new Genero("Mistério")));

        // Adiciona uma versão anterior da série no repositório
        repository.add(serie);

        // Atualiza a série (mesmo título, outros gêneros)
        serie.setGeneros(List.of(new Genero("Ficção Científica")));

        ActionResult result = action.execute(serie);

        assertTrue(result.isSuccess());
        assertEquals("Serie atualizado com sucesso", result.getMessage());
        assertTrue(repository.getItems().contains(serie));
    }

    @Test
    void testErroAoAtualizarSerieInexistente() {
        Serie serie = new Serie();
        serie.setTitulo("Inexistente");
        serie.setGeneros(List.of(new Genero("Drama")));

        // Não adiciona no repositório — simula série inexistente

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().startsWith("Erro!"));
    }
}
