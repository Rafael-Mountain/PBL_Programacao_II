package view.filme;

import controller.dataBase.GeneroRepository;
import controller.action.filme.CreateFilmeAction;
import controller.action.filme.CreateFilmeValidation;
import controller.action.ActionResult;
import model.Filme;
import model.Genero;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CreateFilmeForm extends Screen {
    @Override
    public void draw(Scanner terminal) {
        if (GeneroRepository.getInstance().getItems().isEmpty()) {
            System.out.println("Nenhum genero cadastrado. Cadastre um genero antes de criar um filme.");
            return;
        }

        ActionResult result;
        Filme filme;

        do {
            System.out.println("\n=== Criar Filme ===\n");
            System.out.print("Titulo do filme: ");
            String nome = terminal.nextLine();
            System.out.print("Titulo Original: ");
            String nomeOriginal = terminal.nextLine();

            int AnoLancamento = ViewCommons.inputAno(terminal, "Ano de Lançamento");
            int duracao = ViewCommons.inputInt(terminal, "Duração (em minutos): ");

            System.out.print("Diretor: ");
            String diretor = terminal.nextLine();
            System.out.print("Roteiro: ");
            String roteiro = terminal.nextLine();
            System.out.print("Local disponível: ");
            String localDisponivel = terminal.nextLine();


            List<Genero> generosFilme = ViewCommons.inputGenero(terminal);
            List<String> elenco = ViewCommons.inputElenco(terminal);

            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti? ");

            filme = new Filme(nome, LocalDateTime.of(AnoLancamento, 1, 1, 0, 0),
                    consumido, generosFilme, nomeOriginal, localDisponivel,
                    elenco, diretor, duracao, roteiro);

            CreateFilmeAction createFilme = new CreateFilmeAction(new CreateFilmeValidation());
            result = createFilme.execute(filme);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

        new FilmeDisplay(filme).draw(terminal);

    }
}
