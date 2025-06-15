package tests.controller.action.livro;

import com.mountain_vd.controller.action.livro.CreateLivroValidation;
import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateLivroValidationTest {

    private CreateLivroValidation validation;
    private LivroRepository repository;

    @BeforeEach
    void setUp() {
        validation = new CreateLivroValidation();
        repository = LivroRepository.getInstance();
        repository.getItems().clear(); // Garante que o repositório está limpo
    }

    @Test
    void testTituloVazio() {
        Livro livro = new Livro();
        livro.setTitulo("");
        livro.setIsbn("123");
        livro.setGeneros(List.of(new Genero("Ficção")));

        boolean isValid = validation.isValid(livro);

        assertFalse(isValid);
        assertEquals("Erro! titulo vazio", validation.getErrorMessage());
    }

    @Test
    void testGenerosVazios() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setIsbn("123");
        livro.setGeneros(List.of()); // Lista vazia

        boolean isValid = validation.isValid(livro);

        assertFalse(isValid);
        assertEquals("Erro! Selecione pelo menos um genero", validation.getErrorMessage());
    }

    @Test
    void testLivroComIsbnDuplicado() {
        Livro livroExistente = new Livro();
        livroExistente.setTitulo("Livro Existente");
        livroExistente.setIsbn("123");
        livroExistente.setGeneros(List.of(new Genero("Aventura")));
        repository.add(livroExistente); // Adiciona ao repositório

        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Novo Livro");
        novoLivro.setIsbn("123"); // Mesmo ISBN
        novoLivro.setGeneros(List.of(new Genero("Ficção")));

        boolean isValid = validation.isValid(novoLivro);

        assertFalse(isValid);
        assertEquals("Erro! Já existe um livro com este ISBN na base de dados", validation.getErrorMessage());
    }

    @Test
    void testLivroValido() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Válido");
        livro.setIsbn("456");
        livro.setGeneros(List.of(new Genero("História")));

        boolean isValid = validation.isValid(livro);

        assertTrue(isValid);
        assertNull(validation.getErrorMessage());
    }
}
