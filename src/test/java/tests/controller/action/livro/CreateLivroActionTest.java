package tests.controller.action.livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.livro.CreateLivroAction;
import com.mountain_vd.controller.action.livro.CreateLivroValidation;
import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateLivroActionTest {

    private CreateLivroAction action;
    private LivroRepository repository;

    @BeforeEach
    void setUp() {
        repository = LivroRepository.getInstance();
        repository.getItems().clear(); // Limpa o repositório antes de cada teste
        action = new CreateLivroAction(new CreateLivroValidation());
    }

    @Test
    void testLivroComTituloVazio() {
        Livro livro = new Livro();
        livro.setTitulo("");
        livro.setIsbn("001");
        livro.setGeneros(List.of(new Genero("Drama")));

        ActionResult result = action.execute(livro);

        assertFalse(result.isSuccess());
        assertEquals("Erro! titulo vazio", result.getMessage());
    }

    @Test
    void testLivroComGenerosVazio() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setIsbn("002");
        livro.setGeneros(List.of());

        ActionResult result = action.execute(livro);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Selecione pelo menos um genero", result.getMessage());
    }

    @Test
    void testLivroComIsbnDuplicado() {
        Livro livroExistente = new Livro();
        livroExistente.setTitulo("Existente");
        livroExistente.setIsbn("003");
        livroExistente.setGeneros(List.of(new Genero("Fantasia")));
        repository.add(livroExistente);

        Livro livroNovo = new Livro();
        livroNovo.setTitulo("Novo");
        livroNovo.setIsbn("003"); // ISBN duplicado
        livroNovo.setGeneros(List.of(new Genero("Ficção")));

        ActionResult result = action.execute(livroNovo);

        assertFalse(result.isSuccess());
        assertEquals("Erro! Já existe um livro com este ISBN na base de dados", result.getMessage());
    }

    @Test
    void testLivroValido() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Válido");
        livro.setIsbn("004");
        livro.setGeneros(List.of(new Genero("Aventura")));

        ActionResult result = action.execute(livro);

        assertTrue(result.isSuccess());
        assertEquals("Livro criado com sucesso", result.getMessage());
        assertTrue(repository.getItems().contains(livro));
    }
}
