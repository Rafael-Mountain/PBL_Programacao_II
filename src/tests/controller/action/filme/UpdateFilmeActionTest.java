package tests.controller.action.filme;

import controller.action.filme.UpdateFilmeAction;
import controller.action.ActionResult;
import controller.action.filme.UpdateFilmeValidation;
import controller.dataBase.FilmeRepository;
import model.Filme;
import model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateFilmeActionTest {

    private UpdateFilmeAction action;
    private UpdateFilmeValidation validation;

    @BeforeEach
    void setUp() {
        validation = new UpdateFilmeValidation();
        action = new UpdateFilmeAction(validation);
    }

    @Test
    void testFilmeAtualizadoComSucesso() {
        // Criando o filme e adicionando ao repositório antes da ação
        Filme filme = new Filme();
        filme.setTitulo("Filme Atualizado");
        filme.setGeneros(List.of(new Genero("Ação")));
        FilmeRepository.getInstance().add(filme);

        // Alterando os dados do filme para a atualização
        filme.setTitulo("Filme Atualizado - Novo Título");

        ActionResult result = action.execute(filme);

        assertTrue(result.isSuccess());
        assertEquals("Filme atualizado com sucesso", result.getMessage());

        // Verificando se o filme foi atualizado na base de dados
        Filme filmeAtualizado = FilmeRepository.getInstance().getItemById(filme.getId());
        assertNotNull(filmeAtualizado);
        assertEquals("Filme Atualizado - Novo Título", filmeAtualizado.getTitulo());
    }

    @Test
    void testFilmeComTituloVazio() {
        Filme filme = new Filme();
        filme.setTitulo("");
        filme.setGeneros(List.of(new Genero("Ação")));

        // Adicionando ao repositório antes de passar pela ação
        FilmeRepository.getInstance().add(filme);

        ActionResult result = action.execute(filme);

        assertFalse(result.isSuccess());
        assertEquals("Erro! titulo vazio", result.getMessage());
    }

    @Test
    void testFilmeComGeneroVazio() {
        Filme filme = new Filme();
        filme.setTitulo("Filme Sem Genero");
        filme.setGeneros(List.of());

        // Adicionando ao repositório antes de passar pela ação
        FilmeRepository.getInstance().add(filme);

        ActionResult result = action.execute(filme);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um genero", result.getMessage());
    }

    @Test
    void testFilmeNaoExistente() {
        Filme filme = new Filme();
        filme.setTitulo("Filme Inexistente");
        filme.setGeneros(List.of(new Genero("Drama")));

        filme.setId(999); // Id fictício para um filme que não existe no repositório

        ActionResult result = action.execute(filme);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Filme não encontrado para atualização", result.getMessage());
    }
}
