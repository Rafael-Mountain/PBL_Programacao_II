package tests.controller.action.genero;

import controller.action.ActionResult;
import controller.action.genero.CreateGeneroAction;
import controller.action.genero.CreateGeneroValidation;
import controller.dataBase.GeneroRepository;
import model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateGeneroActionTest {

    private CreateGeneroAction action;

    @BeforeEach
    public void setUp() {
        action = new CreateGeneroAction(new CreateGeneroValidation());
        GeneroRepository.getInstance().getItems().clear();
    }

    @Test
    public void testCreateGeneroComDadosInvalidos() {
        Genero genero = new Genero(""); // nome inválido
        ActionResult result = action.execute(genero);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Nome do gênero não pode ser vazio", result.getMessage());
    }

    @Test
    public void testCreateGeneroComSucesso() {
        Genero genero = new Genero("Fantasia");
        ActionResult result = action.execute(genero);

        assertTrue(result.isSuccess());
        assertEquals("Gênero criado com sucesso", result.getMessage());
        assertTrue(GeneroRepository.getInstance().getItems().contains(genero));
    }

    @Test
    public void testCreateGeneroDuplicado() {
        Genero genero = new Genero("Romance");
        GeneroRepository.getInstance().add(genero);

        ActionResult result = action.execute(new Genero("Romance"));

        assertFalse(result.isSuccess());
        assertEquals("Erro! Gênero já existe na base de dados", result.getMessage());
    }
}
