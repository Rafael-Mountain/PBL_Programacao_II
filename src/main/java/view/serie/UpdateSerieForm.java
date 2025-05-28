package view.serie;

import controller.action.ActionResult;
import controller.action.serie.UpdateSerieAction;
import controller.action.serie.UpdateSerieValidation;
import model.Serie;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.util.Scanner;

public class UpdateSerieForm extends Screen {
    Serie serie;

    public UpdateSerieForm(Serie serie) {
        this. serie =  serie;
    }

    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        System.out.println("\n=== Editar Serie ===");
        do {
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti?");

            serie.setConsumido(consumido);
            UpdateSerieAction updateSerie = new UpdateSerieAction(new UpdateSerieValidation());
            result = updateSerie.execute(serie);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());
    }
}
