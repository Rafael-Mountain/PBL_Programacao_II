package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.temporada.CreateTemporadaAction;
import com.mountain_vd.controller.action.temporada.CreateTemporadaValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Controlador responsável por gerenciar as ações relacionadas ao formulário de Temporada.
 */
public class TemporadaFormController {

    /**
     * Adiciona uma nova temporada à lista de temporadas vinculada a uma série.
     *
     * @param rootScene    A instância da RootScene para exibir mensagens de sucesso ou erro.
     * @param temporadas   A lista observável de temporadas onde a nova temporada será adicionada.
     * @param ano          O ano da temporada, em formato de texto (exemplo: "2024").
     * @param nEpisodes    O número de episódios da temporada, em formato de texto (exemplo: "12").
     * @param serie        A série à qual a temporada está associada.
     */
    public static void addTemporada(RootScene rootScene, ObservableList<Temporada> temporadas, String ano, String nEpisodes, Serie serie) {
        Temporada temporada = new Temporada();

        if (ano != null && !ano.isEmpty()) {
            temporada.setAno(LocalDate.of(Integer.parseInt(ano),1,1));
        }

        if (nEpisodes != null && !nEpisodes.isEmpty()) {
            temporada.setqEpisodios(Integer.parseInt(nEpisodes));
        }

        CreateTemporadaAction action = new CreateTemporadaAction(new CreateTemporadaValidation());
        action.setSuperModel(serie);
        ActionResult result = action.execute(temporada);

        if (result.isSuccess()) {
            temporadas.add(temporada);
            rootScene.showSuccess("Temporada adicionada com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }
}
