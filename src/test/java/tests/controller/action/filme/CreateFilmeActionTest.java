package tests.controller.action.filme;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.filme.CreateFilmeAction;
import com.mountain_vd.controller.action.filme.CreateFilmeValidation;
import com.mountain_vd.controller.dataBase.FilmeRepository;
import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateFilmeActionTest {

    private CreateFilmeAction action;

    @BeforeEach
    void setUp() {
        action = new CreateFilmeAction(new CreateFilmeValidation());
    }

    @Test
    void testTituloVazio() {
        Filme filme = new Filme();
        filme.setTitulo(""); // Título vazio
        filme.setGeneros(List.of(new Genero("Ação")));

        ActionResult result = action.execute(filme);

        assertFalse(result.isSuccess());
        assertEquals("Erro! título vazio", result.getMessage());
    }

    @Test
    void testSemGeneros() {
        Filme filme = new Filme();
        filme.setTitulo("Matrix " + System.nanoTime());
        filme.setGeneros(List.of()); // Lista vazia

        ActionResult result = action.execute(filme);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um gênero", result.getMessage());
    }

    @Test
    void testFilmeDuplicado() {
        Filme filme = new Filme();
        filme.setTitulo("Duplicado " + System.nanoTime()); // Título único
        filme.setGeneros(List.of(new Genero("Ficção")));

        FilmeRepository.getInstance().add(filme); // Adiciona manualmente

        ActionResult result = action.execute(filme); // Tenta adicionar de novo

        assertFalse(result.isSuccess());
        assertEquals("Erro! Filme já existe na base de dados", result.getMessage());
    }

    @Test
    void testFilmeValido() {
        Filme filme = new Filme();
        filme.setTitulo("Filme Teste " + System.nanoTime());
        filme.setGeneros(List.of(new Genero("Drama"), new Genero("Histórico")));

        ActionResult result = action.execute(filme);

        assertTrue(result.isSuccess());
        assertEquals("Filme criado com sucesso", result.getMessage());
        assertTrue(FilmeRepository.getInstance().getItems().contains(filme));
    }
}
