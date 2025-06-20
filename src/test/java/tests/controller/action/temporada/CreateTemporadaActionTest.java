package tests.controller.action.temporada;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.temporada.CreateTemporadaAction;
import com.mountain_vd.controller.action.temporada.CreateTemporadaValidation;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTemporadaActionTest {

    private CreateTemporadaAction action;
    private Serie serie;

    @BeforeEach
    public void setUp() {
        action = new CreateTemporadaAction(new CreateTemporadaValidation());
        serie = new Serie();
        serie.setTitulo("Teste Série");
        SerieRepository.getInstance().getItems().clear(); // Limpar repositório
        SerieRepository.getInstance().add(serie); // Adiciona a série para garantir que ela exista
        action.setSuperModel(serie);
    }

    @Test
    public void testExecuteSemSuperModel() {
        CreateTemporadaAction actionSemSerie = new CreateTemporadaAction(new CreateTemporadaValidation());
        Temporada temporada = new Temporada();
        ActionResult result = actionSemSerie.execute(temporada);

        assertFalse(result.isSuccess());
        assertEquals("Set super model", result.getMessage());
    }

    @Test
    public void testExecuteComSucesso() {
        Temporada temporada = new Temporada();
        ActionResult result = action.execute(temporada);

        assertTrue(result.isSuccess());
        assertEquals("Temporada created successfully", result.getMessage());
        assertTrue(serie.getTemporadas().contains(temporada));
    }
}
