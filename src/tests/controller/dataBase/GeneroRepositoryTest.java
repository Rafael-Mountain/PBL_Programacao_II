package tests.controller.dataBase;

import controller.dataBase.GeneroRepository;
import model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneroRepositoryTest {

    private GeneroRepository repository;

    @BeforeEach
    public void setUp() {
        repository = GeneroRepository.getInstance();
        repository.getItems().clear();  // Resetar lista antes de cada teste
        repository.setGeneroId(0);      // Resetar contador de IDs
    }

    @Test
    public void testAddGenero() {
        Genero genero = new Genero("Ação");
        repository.add(genero);

        List<Genero> lista = repository.getItems();
        assertEquals(1, lista.size());
        assertEquals("Ação", lista.get(0).getNome());
        assertEquals(0, lista.get(0).getId()); // Primeiro ID
    }

    @Test
    public void testGetItemById() {
        Genero genero = new Genero("Comédia");
        repository.add(genero);

        Genero encontrado = repository.getItemById(genero.getId());
        assertNotNull(encontrado);
        assertEquals("Comédia", encontrado.getNome());

        Genero inexistente = repository.getItemById(999);
        assertNull(inexistente);
    }

    @Test
    public void testUpdateGeneroExistente() {
        Genero genero = new Genero("Drama");
        repository.add(genero);

        Genero atualizado = new Genero("Drama Pesado");
        atualizado.setId(genero.getId());
        repository.update(atualizado);

        Genero resultado = repository.getItemById(genero.getId());
        assertEquals("Drama Pesado", resultado.getNome());
    }

    @Test
    public void testUpdateGeneroInexistente() {
        Genero genero = new Genero("Ficção");
        genero.setId(999);

        Exception e = assertThrows(RuntimeException.class, () -> {
            repository.update(genero);
        });

        assertEquals("Genero não encontrado para atualização", e.getMessage());
    }

    @Test
    public void testDeleteGenero() {
        Genero genero = new Genero("Suspense");
        repository.add(genero);
        assertEquals(1, repository.getItems().size());

        repository.delete(genero);
        assertEquals(0, repository.getItems().size());
    }

    @Test
    public void testDeleteGeneroInexistente() {
        Genero genero1 = new Genero("Terror");
        repository.add(genero1);

        Genero genero2 = new Genero("Não Existe");
        genero2.setId(999);
        repository.delete(genero2);  // Não deve lançar exceção

        assertEquals(1, repository.getItems().size());
    }

    @Test
    public void testSetGeneroId() {
        Genero genero = new Genero("Musical");
        repository.setGeneroId(100);
        repository.add(genero);
        assertEquals(100, genero.getId());
    }
}
