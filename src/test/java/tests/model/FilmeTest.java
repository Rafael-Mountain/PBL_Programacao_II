package tests.model;

import com.mountain_vd.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    @Test
    void testConstrutorEGetters() {
        LocalDate dataLancamento = LocalDate.of(2021, 7, 10);
        Genero genero = new Genero("Ação");
        Avaliacao avaliacao = new Avaliacao(LocalDateTime.now(), 5, "Ótimo filme!", LocalDate.of(2021, 7, 11));

        Filme filme = new Filme("Vingadores: Ultimato", dataLancamento, true, Arrays.asList(genero),
                "Avengers: Endgame", "Disney+", Arrays.asList("Robert Downey Jr.", "Chris Evans"),
                "Anthony Russo", 181, "Roteiro maravilhoso");

        filme.avaliar(avaliacao);

        // Testando os valores dos atributos
        assertEquals("Vingadores: Ultimato", filme.getTitulo());
        assertEquals("Avengers: Endgame", filme.getTituloOriginal());
        assertEquals(dataLancamento, filme.getDataLancamento());
        assertTrue(filme.isConsumido());
        assertEquals(1, filme.getGeneros().size());
        assertEquals("Ação", filme.getGeneros().get(0).getNome());
        assertEquals("Disney+", filme.getLocalDisponivel());
        assertEquals(181, filme.getDuracao());
        assertEquals("Anthony Russo", filme.getDirecao());
        assertEquals("Roteiro maravilhoso", filme.getRoteiro());

        // Testando avaliações
        assertEquals(5, filme.getPontuacao());
        assertEquals(1, filme.getAvaliacoes().size());
    }

    @Test
    void testSetters() {
        Filme filme = new Filme();

        filme.setTitulo("Batman: O Cavaleiro das Trevas");
        filme.setDuracao(152);
        filme.setDirecao("Christopher Nolan");
        filme.setRoteiro("Roteiro sombroso e envolvente");

        assertEquals("Batman: O Cavaleiro das Trevas", filme.getTitulo());
        assertEquals(152, filme.getDuracao());
        assertEquals("Christopher Nolan", filme.getDirecao());
        assertEquals("Roteiro sombroso e envolvente", filme.getRoteiro());
    }

}
