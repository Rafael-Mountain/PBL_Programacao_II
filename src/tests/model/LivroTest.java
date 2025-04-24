package tests.model;

import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void testConstrutorEGetters() {
        LocalDateTime dataLancamento = LocalDateTime.of(2021, 5, 15, 10, 0);
        Genero genero = new Genero("Ficção Científica");
        Avaliacao avaliacao = new Avaliacao(LocalDateTime.now(), 4, "Interessante e provocante!", LocalDate.of(2021, 5, 16));

        Livro livro = new Livro("Duna", dataLancamento, true, Arrays.asList(genero),
                "Frank Herbert", "Editora Aleph", true, "978-8539501077");

        livro.Avaliar(avaliacao);

        // Testando os valores dos atributos
        assertEquals("Duna", livro.getTitulo());
        assertEquals("Frank Herbert", livro.getAutor());
        assertEquals("Editora Aleph", livro.getEditora());
        assertTrue(livro.isPossui());
        assertEquals("978-8539501077", livro.getIsbn());
        assertEquals(1, livro.getGeneros().size());
        assertEquals("Ficção Científica", livro.getGeneros().get(0).getNome());

        // Testando avaliações
        assertEquals(4, livro.getPontuacao());
        assertEquals(1, livro.getAvaliacoes().size());
    }

    @Test
    void testSetters() {
        Livro livro = new Livro();

        livro.setTitulo("O Senhor dos Anéis");
        livro.setAutor("J.R.R. Tolkien");
        livro.setEditora("HarperCollins");
        livro.setPossui(true);
        livro.setIsbn("978-0261103573");

        assertEquals("O Senhor dos Anéis", livro.getTitulo());
        assertEquals("J.R.R. Tolkien", livro.getAutor());
        assertEquals("HarperCollins", livro.getEditora());
        assertTrue(livro.isPossui());
        assertEquals("978-0261103573", livro.getIsbn());
    }

    @Test
    void testAvaliar() {
        Livro livro = new Livro("O Hobbit", LocalDateTime.of(2018, 4, 12, 15, 30), true,
                Arrays.asList(new Genero("Aventura")), "J.R.R. Tolkien", "HarperCollins", true, "978-0261103566");

        Avaliacao avaliacao1 = new Avaliacao(LocalDateTime.now(), 5, "Excelente história!", LocalDate.of(2018, 4, 12));
        Avaliacao avaliacao2 = new Avaliacao(LocalDateTime.now().minusDays(1), 4, "Bom livro, mas com alguns pontos fracos.", LocalDate.of(2018, 4, 10));

        livro.Avaliar(avaliacao1);
        livro.Avaliar(avaliacao2);

        assertEquals(5, livro.getPontuacao()); // Deve retornar a avaliação mais recente
        assertEquals(2, livro.getAvaliacoes().size());
    }
}
