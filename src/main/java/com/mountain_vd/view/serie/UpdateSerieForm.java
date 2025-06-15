package com.mountain_vd.view.serie;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;

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
