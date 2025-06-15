package tests.controller.action.serie;

import com.mountain_vd.controller.action.serie.CreateSerieAction;
import com.mountain_vd.controller.action.serie.CreateSerieValidation;
import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSerieActionTest {

    private CreateSerieAction action;
    private CreateSerieValidation validation;

    @BeforeEach
    void setUp() {
        validation = new CreateSerieValidation();
        action = new CreateSerieAction(validation);
    }

    @Test
    void testSerieCriadaComSucesso() {
        // Criando o filme e adicionando ao repositório
        Serie serie = new Serie();
        serie.setTitulo("Nova Série");
        serie.setGeneros(List.of(new Genero("Aventura")));

        // Verificando se o repositório está vazio antes de adicionar

        ActionResult result = action.execute(serie);

        // Verificando se a série foi criada com sucesso
        assertTrue(result.isSuccess());
        assertEquals("Série criada com sucesso", result.getMessage());

        // Verificando se a série foi adicionada ao repositório
        assertTrue(SerieRepository.getInstance().getItems().contains(serie));
    }

    @Test
    void testSerieComTituloVazio() {
        Serie serie = new Serie();
        serie.setTitulo("");
        serie.setGeneros(List.of(new Genero("Aventura")));

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! titulo vazio", result.getMessage());
    }

    @Test
    void testSerieComGeneroVazio() {
        Serie serie = new Serie();
        serie.setTitulo("Série Sem Gênero");
        serie.setGeneros(List.of());

        ActionResult result = action.execute(serie);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um genero", result.getMessage());
    }

    @Test
    void testSerieJaExistente() {
        Serie serieExistente = new Serie();
        serieExistente.setTitulo("Série Existente");
        serieExistente.setGeneros(List.of(new Genero("Comédia")));
        SerieRepository.getInstance().add(serieExistente); // Adiciona no repositório

        Serie serieNova = new Serie();
        serieNova.setTitulo("Série Existente");
        serieNova.setGeneros(List.of(new Genero("Comédia"))); // Mesmo título e gênero

        ActionResult result = action.execute(serieNova);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Serie ja existe na base de dados", result.getMessage());
    }
}
