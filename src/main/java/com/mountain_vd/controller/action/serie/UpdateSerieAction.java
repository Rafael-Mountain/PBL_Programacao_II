package com.mountain_vd.controller.action.serie;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.BaseAction;
import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;

/**
 * Ação responsável pela atualização de uma série existente no sistema.
 *
 * Esta classe executa a lógica de atualização de uma série. Ela valida os dados fornecidos para a série
 * antes de proceder com a atualização no repositório de séries. Caso a validação falhe, a ação retorna um
 * resultado de falha com a respectiva mensagem de erro. Caso contrário, a série é atualizada com sucesso.
 *
 * @see ActionResult
 * @see Validation
 * @see Serie
 * @see SerieRepository
 */
public class UpdateSerieAction extends BaseAction<Serie> {

    /**
     * Construtor da ação de atualização de série.
     *
     * @param validation O objeto {@link Validation} usado para validar a série antes da atualização.
     */
    public UpdateSerieAction(Validation<Serie> validation) {
        super(validation);
    }

    /**
     * Executa a ação de atualização da série.
     *
     * Este método valida os dados fornecidos, e caso os dados estejam válidos, atualiza a série no repositório.
     * Se a validação falhar, a ação retorna um resultado indicando o erro. Se a atualização for bem-sucedida,
     * um resultado indicando sucesso é retornado.
     *
     * @param model A série a ser atualizada.
     * @return O resultado da ação, incluindo o status de sucesso ou falha e a mensagem associada.
     */
    @Override
    public ActionResult execute(Serie model) {
        // Verifica se os dados fornecidos são válidos
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        try{
            SerieRepository.getInstance().update(model);
        } catch (Exception e) {
            return new ActionResult(false, "Erro! " + e.getMessage());
        }

        return new ActionResult(true, "Serie atualizado com sucesso");
    }
}
