package tests.controller.dataBase;

import controller.dataBase.LivroRepository;
import model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LivroRepositoryTest {

    private LivroRepository repository;

    @BeforeEach
    public void setUp() {
        repository = LivroRepository.getInstance();
        repository.getItems().clear();  // Limpa os dados antes de cada teste
        repository.clear();      // Reinicia o contador de ID
    }

    @Test
    public void testAddLivro() {
        Livro livro = new Livro();
        livro.setTitulo("O Senhor dos Anéis");
        repository.add(livro);

        List<Livro> livros = repository.getItems();
        assertEquals(1, livros.size());
        assertEquals("O Senhor dos Anéis", livros.get(0).getTitulo());
        assertEquals(0, livros.get(0).getId());
    }

    @Test
    public void testGetItemById() {
        Livro livro = new Livro();
        livro.setTitulo("1984");
        repository.add(livro);

        Livro encontrado = repository.getItemById(livro.getId());
        assertNotNull(encontrado);
        assertEquals("1984", encontrado.getTitulo());

        Livro inexistente = repository.getItemById(999);
        assertNull(inexistente);
    }

    @Test
    public void testUpdateLivroExistente() {
        Livro livro = new Livro();
        livro.setTitulo("O Hobbit");
        repository.add(livro);

        Livro atualizado = new Livro();
        atualizado.setTitulo("O Hobbit - Edição Especial");
        atualizado.setId(livro.getId());
        repository.update(atualizado);

        Livro resultado = repository.getItemById(livro.getId());
        assertEquals("O Hobbit - Edição Especial", resultado.getTitulo());
    }

    @Test
    public void testUpdateLivroInexistente() {
        Livro livro = new Livro();
        livro.setTitulo("A Metamorfose");
        livro.setId(999);

        Exception e = assertThrows(RuntimeException.class, () -> {
            repository.update(livro);
        });

        assertEquals("Livro não encontrado para atualização.", e.getMessage());
    }

    @Test
    public void testDeleteLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        repository.add(livro);
        assertEquals(1, repository.getItems().size());

        repository.delete(livro);
        assertEquals(0, repository.getItems().size());
    }

    @Test
    public void testDeleteLivroInexistente() {
        Livro livro1 = new Livro();
        livro1.setTitulo("A Moreninha");
        repository.add(livro1);

        Livro livro2 = new Livro();
        livro2.setTitulo("Inexistente");
        livro2.setId(999);
        repository.delete(livro2); // Não lança exceção

        assertEquals(1, repository.getItems().size());
    }

    @Test
    public void testSetLivroId() {
        Livro livro = new Livro();
        livro.setTitulo("A Revolução dos Bichos");
        repository.add(livro);

        assertEquals(0, livro.getId());
    }
}
