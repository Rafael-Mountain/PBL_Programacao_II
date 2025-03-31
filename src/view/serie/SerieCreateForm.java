package view.serie;

import controller.action.serie.CreateSerieValidation;
import controller.dataBase.GeneroRepository;
import controller.action.serie.CreateSerieAction;
import controller.action.commons.ActionResult;
import model.Serie;
import model.Genero;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SerieCreateForm extends Screen {
    //todo: Testar to com muito sono para ver se funciona
    @Override
    public void draw(Scanner terminal) {
        if (GeneroRepository.getInstance().getItems().isEmpty()) {
            System.out.println("Nenhum genero cadastrado. Cadastre um genero antes de criar uma série.");
            return;
        }

        ActionResult result;
        Serie serie;

        do {
            System.out.println("\n=== Criar Série ===\n");
            System.out.print("Titulo da série: ");
            String nome = terminal.nextLine();
            System.out.print("Titulo Original: ");
            String nomeOriginal = terminal.nextLine();

            int AnoLancamento = ViewCommons.inputAnoLancamento(terminal);
            System.out.print("Local disponível: ");
            String localDisponivel = terminal.nextLine();

            List<Genero> generosSerie = ViewCommons.inputGenero(terminal);
            List<String> elenco = ViewCommons.inputElenco(terminal);

            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti? ");

            serie = new Serie(nome, LocalDateTime.of(AnoLancamento, 1, 1, 0, 0),
                    consumido, generosSerie, nomeOriginal, localDisponivel,
                    elenco);
            CreateSerieAction createSerie = new CreateSerieAction(new CreateSerieValidation());
            result = createSerie.execute(serie);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

    }
}