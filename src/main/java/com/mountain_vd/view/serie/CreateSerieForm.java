package com.mountain_vd.view.serie;

import com.mountain_vd.controller.action.serie.CreateSerieValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.action.serie.CreateSerieAction;
import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Genero;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CreateSerieForm extends Screen {
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

            int anoLancamento = ViewCommons.inputAno(terminal, "Ano de Lançamento");
            int datafim = ViewCommons.inputAno(terminal, "Ano de Encerramento");
            System.out.print("Local disponível: ");
            String localDisponivel = terminal.nextLine();

            List<Genero> generosSerie = ViewCommons.inputGenero(terminal);
            List<String> elenco = ViewCommons.inputElenco(terminal);

            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti? ");

            serie = new Serie(nome, LocalDateTime.of(anoLancamento, 1, 1, 0, 0),
                    consumido, generosSerie, nomeOriginal, localDisponivel,
                    elenco, LocalDateTime.of(datafim, 1, 1, 0, 0));
            CreateSerieAction createSerie = new CreateSerieAction(new CreateSerieValidation());
            result = createSerie.execute(serie);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

       new SerieDisplay(serie).draw(terminal);
    }
}