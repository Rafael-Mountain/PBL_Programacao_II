package tests.controller.dataBase;

import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SerieRepositoryTest {

    private SerieRepository repository;

    @BeforeEach
    public void setUp() {
        repository = SerieRepository.getInstance();
        repository.getItems().clear();  // Limpa antes de cada teste
        repository.clear();       // Reinicia o contador de ID
    }

    @Test
    public void testAddSerie() {
        Serie serie = new Serie();
        serie.setTitulo("Breaking Bad");
        repository.add(serie);

        List<Serie> series = repository.getItems();
        assertEquals(1, series.size());
        assertEquals("Breaking Bad", series.get(0).getTitulo());
        assertEquals(0, series.get(0).getId());
    }

    @Test
    public void testGetItemById() {
        Serie serie = new Serie();
        serie.setTitulo("Stranger Things");
        repository.add(serie);

        Serie encontrada = repository.getItemById(serie.getId());
        assertNotNull(encontrada);
        assertEquals("Stranger Things", encontrada.getTitulo());

        Serie inexistente = repository.getItemById(999);
        assertNull(inexistente);
    }

    @Test
    public void testUpdateSerieExistente() {
        Serie serie = new Serie();
        serie.setTitulo("Dark");
        repository.add(serie);

        Serie atualizada = new Serie();
        atualizada.setTitulo("Dark - Final");
        atualizada.setId(serie.getId());
        repository.update(atualizada);

        Serie resultado = repository.getItemById(serie.getId());
        assertEquals("Dark - Final", resultado.getTitulo());
    }
}
