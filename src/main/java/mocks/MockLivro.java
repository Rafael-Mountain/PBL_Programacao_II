package mocks;

import controller.action.livro.CreateLivroAction;
import controller.action.livro.CreateLivroValidation;
import controller.dataBase.GeneroRepository;
import model.Genero;
import model.Livro;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockLivro {
    public static void rodar() {
        List<Genero> g = GeneroRepository.getInstance().getItems();
        if (g.isEmpty()) {
            MockGenero.rodar();
        }

        CreateLivroAction action = new CreateLivroAction(new CreateLivroValidation());

        List<Livro> livros = Arrays.asList(
                new Livro("Duna", LocalDateTime.of(1965, 8, 1, 0, 0), true, g.subList(0, 1), "Frank Herbert", "Aleph", true, "9788576573134"),
                new Livro("Harry Potter e a Pedra Filosofal", LocalDateTime.of(1997, 6, 26, 0, 0), true, g.subList(1, 2), "J.K. Rowling", "Rocco", true, "9788532511010"),
                new Livro("Orgulho e Preconceito", LocalDateTime.of(1813, 1, 28, 0, 0), false, g.subList(2, 3), "Jane Austen", "Penguin", false, "9780141439518"),
                new Livro("O Código Da Vinci", LocalDateTime.of(2003, 3, 18, 0, 0), true, g.subList(3, 4), "Dan Brown", "Sextante", false, "9788575421139"),
                new Livro("Steve Jobs", LocalDateTime.of(2011, 10, 24, 0, 0), false, g.subList(4, 5), "Walter Isaacson", "Companhia das Letras", true, "9788535920413"),
                new Livro("Sapiens", LocalDateTime.of(2011, 6, 4, 0, 0), false, g.subList(5, 6), "Yuval Noah Harari", "L&PM", false, "9788578270697"),
                new Livro("Clean Code", LocalDateTime.of(2008, 8, 1, 0, 0), true, g.subList(6, 7), "Robert C. Martin", "Alta Books", true, "9788576082674"),
                new Livro("O Poder do Hábito", LocalDateTime.of(2012, 2, 28, 0, 0), false, g.subList(7, 8), "Charles Duhigg", "Objetiva", true, "9788539004119"),
                new Livro("A Ilha do Tesouro", LocalDateTime.of(1883, 11, 14, 0, 0), false, g.subList(8, 9), "Robert Louis Stevenson", "Zahar", false, "9788537811061"),
                new Livro("It: A Coisa", LocalDateTime.of(1986, 9, 15, 0, 0), false, g.subList(9, 10), "Stephen King", "Suma", false, "9788544105080")
        );

        for (Livro livro : livros) {
            action.execute(livro);
        }
    }
}
