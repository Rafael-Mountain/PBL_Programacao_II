package tests.controller.dataBase;

import controller.dataBase.FilmeRepository;
import model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeRepositoryTest {

    private FilmeRepository repository;

    @BeforeEach
    public void setUp() {
        repository = FilmeRepository.getInstance();
        repository.getItems().clear();
        repository.setFilmeId(0);
        // limpa antes de cada teste
    }

    @Test
    public void testAddFilme() {
        Filme filme = new Filme();
        filme.setTitulo("A Origem");
        repository.add(filme);

        List<Filme> filmes = repository.getItems();
        assertEquals(1, filmes.size());
        assertEquals("A Origem", filmes.get(0).getTitulo());
        assertEquals(0, filmes.get(0).getId()); // primeiro ID deve ser 0
    }

    @Test
    public void testGetItemById() {
        Filme filme = new Filme();
        filme.setTitulo("Interestelar");
        repository.add(filme);

        Filme encontrado = repository.getItemById(filme.getId());
        assertNotNull(encontrado);
        assertEquals("Interestelar", encontrado.getTitulo());

        Filme naoEncontrado = repository.getItemById(999);
        assertNull(naoEncontrado);
    }

    @Test
    public void testUpdateFilmeExistente() {
        Filme filme = new Filme();
        filme.setTitulo("Matrix");
        repository.add(filme);

        Filme atualizado = new Filme();
        atualizado.setTitulo("Matrix Reloaded");
        atualizado.setId(filme.getId());
        repository.update(atualizado);

        Filme resultado = repository.getItemById(filme.getId());
        assertEquals("Matrix Reloaded", resultado.getTitulo());
    }

    @Test
    public void testUpdateFilmeInexistente() {
        Filme filme = new Filme();
        filme.setTitulo("Filme inexistente");
        filme.setId(999);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.update(filme);
        });

        assertEquals("Filme não encontrado para atualização", exception.getMessage());
    }

    @Test
    public void testDeleteFilme() {
        Filme filme = new Filme();
        filme.setTitulo("O Senhor dos Anéis");
        repository.add(filme);

        assertEquals(1, repository.getItems().size());

        repository.delete(filme);

        assertEquals(0, repository.getItems().size());
    }

    @Test
    public void testDeleteFilmeInexistente() {
        Filme filme1 = new Filme();
        filme1.setTitulo("Shrek");
        repository.add(filme1);

        Filme filme2 = new Filme();
        filme2.setTitulo("Filme Inexistente");
        filme2.setId(999);
        repository.delete(filme2); // Não deve lançar erro

        assertEquals(1, repository.getItems().size()); // Ainda contém Shrek
    }
}
