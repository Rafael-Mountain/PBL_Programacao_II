package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoAction;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoValidation;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.commons.IAvaliavel;
import com.mountain_vd.viewFX.RootScene;

import java.time.LocalDate;
import java.util.List;

public class AvaliacaoFormController {

    public static void adicionarAvaliacao(RootScene rootScene, IAvaliavel entity, LocalDate date, int nota, String comentario, List<Avaliacao> avaliacoes) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDataConsumo(date);
        avaliacao.setPontuacao(nota);
        avaliacao.setReview(comentario);

        CreateAvaliacaoAction action = new CreateAvaliacaoAction(new CreateAvaliacaoValidation());
        action.setSuperModel(entity);
        ActionResult result = action.execute(avaliacao);

        if (result.isSuccess()) {
            avaliacoes.add(avaliacao);
            rootScene.showSuccess("Avaliação adicionada com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }
}
