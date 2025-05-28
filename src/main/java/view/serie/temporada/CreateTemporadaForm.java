package view.serie.temporada;

import controller.action.ActionResult;
import controller.action.temporada.CreateTemporadaAction;
import controller.action.temporada.CreateTemporadaValidation;
import model.Serie;
import model.Temporada;
import view.commons.Screen;
import view.commons.ViewCommons;
import view.serie.SerieDisplay;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateTemporadaForm extends Screen {
    private Serie serie;
    private ActionResult result;

    public CreateTemporadaForm( Serie serie) {
        this.serie = serie;
    }

    @Override
    public void draw(Scanner terminal) {
        do {
            System.out.println("\n=== Adicionar Temporada ===");

            int ano = ViewCommons.inputAno(terminal, "Digite o ano da temporada");
            int qEpisodios = ViewCommons.inputInt(terminal, "Digite a quantidade de episodios: ");

            Temporada temporada = new Temporada(qEpisodios,
                    LocalDateTime.of(ano, 1, 1, 0, 0));

            CreateTemporadaAction createTemporadaAction = new CreateTemporadaAction(new CreateTemporadaValidation());
            createTemporadaAction.setSuperModel(serie);
            result = createTemporadaAction.execute(temporada);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

        new SerieDisplay(serie).draw(terminal);
    }
}
