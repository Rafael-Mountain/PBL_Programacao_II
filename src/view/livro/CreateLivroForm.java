package view.livro;

import controller.action.ActionResult;
import controller.action.filme.CreateFilmeAction;
import controller.action.filme.CreateFilmeValidation;
import controller.action.livro.CreateLivroAction;
import controller.action.livro.CreateLivroValidation;
import controller.dataBase.GeneroRepository;
import model.Filme;
import model.Genero;
import model.Livro;
import view.commons.Screen;
import view.commons.ViewCommons;
import view.filme.FilmeDisplay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CreateLivroForm extends Screen {
    @Override
    public void draw(Scanner terminal) {
        if (GeneroRepository.getInstance().getItems().isEmpty()) {
            System.out.println("Nenhum genero cadastrado. Cadastre um genero antes de criar um livro.");
            return;
        }

        ActionResult result;
        Livro livro;

        do {
            System.out.println("\n=== Criar Livero ===\n");

            System.out.print("Titulo do livro: ");
            String nome = terminal.nextLine();

            System.out.print("Autor: ");
            String autor = terminal.nextLine();

            System.out.print("Editora: ");
            String editora = terminal.nextLine();

            System.out.print("ISBN:");
            String isbn = terminal.nextLine();

            int AnoLancamento = ViewCommons.inputAno(terminal, "Ano de Lançamento");

            List<Genero> generosLivro = ViewCommons.inputGenero(terminal);

            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Leu? ");

            boolean possui = ViewCommons.inputBoolean(terminal, "Possui? ");

            livro = new Livro(nome, LocalDateTime.of(AnoLancamento, 1, 1, 0, 0),
                    consumido, generosLivro, autor, editora, possui, isbn);

            System.out.println(livro.getTitulo());
            CreateLivroAction createLivro = new CreateLivroAction(new CreateLivroValidation());
            result = createLivro.execute(livro);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

        new LivroDisplay(livro).draw(terminal);

    }
}
