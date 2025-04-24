package tests.model;

import model.Avaliacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvaliacaoTest {

    @Test
    void testConstrutorEGetters() {
        LocalDateTime dataAvaliacao = LocalDateTime.of(2022, 5, 1, 10, 30);
        LocalDate dataConsumo = LocalDate.of(2022, 4, 20);
        Avaliacao avaliacao = new Avaliacao(dataAvaliacao, 4, "Muito bom", dataConsumo);

        assertEquals(dataAvaliacao, avaliacao.getDataAvaliacao());
        assertEquals(4, avaliacao.getPontuacao());
        assertEquals("Muito bom", avaliacao.getReview());
        assertEquals(dataConsumo, avaliacao.getDataConsumo());
    }

    @Test
    void testSetters() {
        Avaliacao avaliacao = new Avaliacao(
                LocalDateTime.of(2022, 5, 1, 10, 30),
                3,
                "Razoável",
                LocalDate.of(2022, 4, 20)
        );

        avaliacao.setPontuacao(5);
        avaliacao.setReview("Excelente!");
        avaliacao.setDataAvaliacao(LocalDateTime.of(2023, 6, 15, 14, 0));
        avaliacao.setDataConsumo(LocalDate.of(2023, 6, 1));

        assertEquals(5, avaliacao.getPontuacao());
        assertEquals("Excelente!", avaliacao.getReview());
        assertEquals(LocalDateTime.of(2023, 6, 15, 14, 0), avaliacao.getDataAvaliacao());
        assertEquals(LocalDate.of(2023, 6, 1), avaliacao.getDataConsumo());
    }

}
