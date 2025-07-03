package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoAction;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoValidation;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.commons.IAvaliavel;
import com.mountain_vd.viewFX.RootScene;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador responsável por gerenciar a criação e adição de avaliações
 * para entidades que implementam a interface {@link IAvaliavel}.
 */
public class AvaliacaoFormController {

    /**
     * Adiciona uma nova avaliação para a entidade avaliada, executando a validação e atualização
     * da lista de avaliações caso a operação seja bem-sucedida.
     *
     * @param rootScene a cena raiz da aplicação para exibir mensagens de feedback
     * @param entity a entidade que será avaliada (deve implementar {@link IAvaliavel})
     * @param date data do consumo/avaliação
     * @param nota pontuação atribuída na avaliação
     * @param comentario comentário textual da avaliação
     * @param avaliacoes lista onde a nova avaliação será adicionada caso seja válida
     */
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
