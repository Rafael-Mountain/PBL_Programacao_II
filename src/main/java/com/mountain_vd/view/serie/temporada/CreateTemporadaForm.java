package com.mountain_vd.view.serie.temporada;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.temporada.CreateTemporadaAction;
import com.mountain_vd.controller.action.temporada.CreateTemporadaValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;
import com.mountain_vd.view.serie.SerieDisplay;

import java.time.LocalDate;
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
                    LocalDate.of(ano, 1, 1));

            CreateTemporadaAction createTemporadaAction = new CreateTemporadaAction(new CreateTemporadaValidation());
            createTemporadaAction.setSuperModel(serie);
            result = createTemporadaAction.execute(temporada);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

        new SerieDisplay(serie).draw(terminal);
    }
}
