package tests.controller.action.serie;

import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Genero;
import com.mountain_vd.controller.action.ActionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateSerieActionTest {

    private UpdateSerieValidation validation;
    private UpdateSerieAction action;

    @BeforeEach
    void setUp() {
        validation = new UpdateSerieValidation();
        action = new UpdateSerieAction(validation);

        // Limpa o repositório antes de cada teste
        SerieRepository.getInstance().getItems().clear();
    }

    @Test
    void testUpdateSerieTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo("");  // Título vazio
        serie.setGeneros(List.of(new Genero("Aventura")));

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! titulo vazio", result.getMessage());
    }

    @Test
    void testUpdateSerieGeneroVazio() {
        Serie serie = new Serie();
        serie.setTitulo("Série Sem Gênero");
        serie.setGeneros(List.of());  // Gêneros vazios

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um genero", result.getMessage());
    }

    @Test
    void testUpdateSerieValida() {
        // Adiciona uma série no repositório
        Serie serieExistente = new Serie();
        serieExistente.setTitulo("Série Existente");
        serieExistente.setGeneros(List.of(new Genero("Aventura")));
        SerieRepository.getInstance().add(serieExistente);

        // Atualiza a série
        serieExistente.setTitulo("Série Atualizada");
        serieExistente.setGeneros(List.of(new Genero("Comédia")));

        ActionResult result = action.execute(serieExistente);

        assertTrue(result.isSuccess());
        assertEquals("Serie atualizado com sucesso", result.getMessage());
    }

    @Test
    void testUpdateSerieNaoExistente() {
        Serie serieNaoExistente = new Serie();
        serieNaoExistente.setTitulo("Série Não Existente");
        serieNaoExistente.setGeneros(List.of(new Genero("Drama")));

        ActionResult result = action.execute(serieNaoExistente);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Série não encontrada para atualização.", result.getMessage());
    }
}
