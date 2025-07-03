package tests.model;

import com.mountain_vd.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

class TemporadaTest {

    @Test
    void testConstrutorEGetters() {
        LocalDate ano = LocalDate.of(2021, 9, 1);
        Avaliacao avaliacao = new Avaliacao(LocalDateTime.now(), 4, "Boa temporada, mas poderia ter mais ação!", LocalDate.of(2021, 9, 1));

        Temporada temporada = new Temporada(10, ano);
        temporada.setId(1);
        temporada.setSerieId(101);
        temporada.avaliar(avaliacao);

        // Testando os valores dos atributos
        assertEquals(1, temporada.getId());
        assertEquals(101, temporada.getSerieId());
        assertEquals(10, temporada.getqEpisodios());
        assertEquals(ano, temporada.getAno());
        assertEquals(4, temporada.getPontuacao());  // Última avaliação
        assertEquals(1, temporada.getAvaliacoes().size());
    }

    @Test
    void testSetters() {
        Temporada temporada = new Temporada(12, LocalDate.of(2022, 5, 1));

        temporada.setId(2);
        temporada.setSerieId(202);
        temporada.setAno(LocalDate.of(2023, 6, 15));
        temporada.setqEpisodios(15);

        assertEquals(2, temporada.getId());
        assertEquals(202, temporada.getSerieId());
        assertEquals(15, temporada.getqEpisodios());
        assertEquals(LocalDate.of(2023, 6, 15), temporada.getAno());
    }

    @Test
    void testAvaliar() {
        Temporada temporada = new Temporada(8, LocalDate.of(2020, 11, 1));
        Avaliacao avaliacao1 = new Avaliacao(LocalDateTime.now(), 3, "Boa, mas alguns episódios são arrastados.", LocalDate.of(2020, 11, 2));
        Avaliacao avaliacao2 = new Avaliacao(LocalDateTime.now().minusDays(1), 4, "Ótima temporada, gostei muito!", LocalDate.of(2020, 11, 1));

        temporada.avaliar(avaliacao1);
        temporada.avaliar(avaliacao2);

        assertEquals(3, temporada.getPontuacao()); // Deve retornar a avaliação mais recente
        assertEquals(2, temporada.getAvaliacoes().size());
    }

    @Test
    void testPontuacaoComAvaliacoes() {
        Temporada temporada = new Temporada(10, LocalDate.of(2021, 3, 1));
        Avaliacao avaliacao1 = new Avaliacao(LocalDateTime.now(), 5, "Excelente temporada!", LocalDate.of(2021, 3, 1));
        Avaliacao avaliacao2 = new Avaliacao(LocalDateTime.now().minusDays(2), 3, "Pode melhorar.", LocalDate.of(2021, 2, 28));

        temporada.avaliar(avaliacao1);
        temporada.avaliar(avaliacao2);

        // Testando a pontuação mais recente
        assertEquals(5, temporada.getPontuacao()); // A avaliação mais recente deve ser usada
    }

    @Test
    void testAvaliarComListaVazia() {
        Temporada temporada = new Temporada(5, LocalDate.of(2022, 7, 15));

        // Testa a pontuação quando não há avaliações
        assertEquals(0, temporada.getPontuacao());
        assertTrue(temporada.getAvaliacoes().isEmpty());
    }
}
