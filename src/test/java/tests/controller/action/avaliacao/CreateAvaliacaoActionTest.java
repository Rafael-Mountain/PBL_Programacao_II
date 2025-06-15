package tests.controller.action.avaliacao;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoAction;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoValidation;
import com.mountain_vd.controller.dataBase.FilmeRepository;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAvaliacaoActionTest {

    private CreateAvaliacaoValidation validation;
    private CreateAvaliacaoAction action;
    private Avaliacao avaliacaoValida;

    @BeforeEach
    void setUp() {
        validation = new CreateAvaliacaoValidation();
        action = new CreateAvaliacaoAction(validation);

        avaliacaoValida = new Avaliacao();
        avaliacaoValida.setDataAvaliacao(LocalDateTime.now());
        avaliacaoValida.setPontuacao(4);
        avaliacaoValida.setReview("Muito bom");
        avaliacaoValida.setDataConsumo(LocalDate.now());
    }

    @Test
    void testValidacaoPontuacaoInvalida() {
        Avaliacao avaliacaoInvalida = new Avaliacao();
        avaliacaoInvalida.setDataAvaliacao(LocalDateTime.now());
        avaliacaoInvalida.setPontuacao(0);
        avaliacaoInvalida.setReview("Ruim");
        avaliacaoInvalida.setDataConsumo(LocalDate.now());

        ActionResult result = action.execute(avaliacaoInvalida);
        assertFalse(result.isSuccess());
    }

    @Test
    void testAvaliacaoComFilme() {
        Filme filme = new Filme();
        filme.setTitulo("Interestelar");

        Genero generoFiccao = new Genero("Ficção científica");
        filme.setGeneros(Collections.singletonList(generoFiccao));

        filme.setDataLancamento(LocalDateTime.of(2014, 11, 7, 0, 0));
        filme.setId(0);

        FilmeRepository.getInstance().add(filme);

        action.setSuperModel(filme);
        ActionResult result = action.execute(avaliacaoValida);

        assertTrue(result.isSuccess());
        assertEquals(1, filme.getAvaliacoes().size());
        assertEquals(avaliacaoValida.getPontuacao(), filme.getPontuacao());
    }

    @Test
    void testAvaliacaoComLivro() {
        Livro livro = new Livro();
        livro.setTitulo("O Hobbit");

        Genero generoFantasia = new Genero("Fantasia");
        livro.addGenero(generoFantasia);

        livro.setAutor("J.R.R. Tolkien");
        livro.setId(0);

        LivroRepository.getInstance().add(livro);

        action.setSuperModel(livro);
        ActionResult result = action.execute(avaliacaoValida);

        assertTrue(result.isSuccess());
        assertEquals(1, livro.getAvaliacoes().size());
    }

    @Test
    void testAvaliacaoComTemporada() {
        Temporada temporada = new Temporada();
        temporada.setqEpisodios(8);
        temporada.setAno(LocalDateTime.of(2020, 5, 1, 0, 0));
        temporada.setId(0);
        temporada.setSerieId(0);

        Serie serie = new Serie();
        serie.setTitulo("Stranger Things");
        serie.setId(0);

        SerieRepository.getInstance().add(serie);
        serie.addTemporada(temporada);

        action.setSuperModel(temporada);

        ActionResult result = action.execute(avaliacaoValida);

        assertTrue(result.isSuccess());

        Serie serieAtualizada = SerieRepository.getInstance().getItemById(0);
        assertNotNull(serieAtualizada);
        assertEquals(1, serieAtualizada.getTemporadas().get(0).getAvaliacoes().size());
    }

    @Test
    void testSuperModelNulo() {
        action.setSuperModel(null);
        ActionResult result = action.execute(avaliacaoValida);

        assertFalse(result.isSuccess());
        assertEquals("Super model is null", result.getMessage());
    }
}
