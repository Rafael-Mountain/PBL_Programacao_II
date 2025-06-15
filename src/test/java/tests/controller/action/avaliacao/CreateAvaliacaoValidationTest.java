package tests.controller.action.avaliacao;

import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoValidation;
import com.mountain_vd.model.Avaliacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAvaliacaoValidationTest {

    private CreateAvaliacaoValidation validation;

    @BeforeEach
    void setUp() {
        validation = new CreateAvaliacaoValidation();
    }

    @Test
    void testAvaliacaoNula() {
        boolean isValid = validation.isValid(null);
        assertFalse(isValid);
        assertEquals("Avaliacao está nula", validation.getErrorMessage());
    }

    @Test
    void testPontuacaoMenorQueUm() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPontuacao(0);
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        avaliacao.setReview("Ruim");
        avaliacao.setDataConsumo(LocalDate.now());

        boolean isValid = validation.isValid(avaliacao);
        assertFalse(isValid);
        assertEquals("Pontuação inválida: deve estar entre 1 e 5.", validation.getErrorMessage());
    }

    @Test
    void testPontuacaoMaiorQueCinco() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPontuacao(6);
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        avaliacao.setReview("Excelente, mas inválida");
        avaliacao.setDataConsumo(LocalDate.now());

        boolean isValid = validation.isValid(avaliacao);
        assertFalse(isValid);
        assertEquals("Pontuação inválida: deve estar entre 1 e 5.", validation.getErrorMessage());
    }

    @Test
    void testPontuacaoValida() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPontuacao(4);
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        avaliacao.setReview("Muito bom");
        avaliacao.setDataConsumo(LocalDate.now());

        boolean isValid = validation.isValid(avaliacao);
        assertTrue(isValid);
        assertNull(validation.getErrorMessage());
    }
}
