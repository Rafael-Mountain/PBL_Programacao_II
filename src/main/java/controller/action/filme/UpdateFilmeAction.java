package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Filme;

/**
 * Ação responsável pela atualização de um filme no sistema.
 *
 * Esta classe extende {@link BaseAction} e implementa a lógica para atualizar um filme
 * no repositório, validando se a operação de atualização pode ser realizada com base
 * na validação do modelo de filme.
 *
 * @see BaseAction
 * @see Validation
 * @see FilmeRepository
 * @see Filme
 */
public class UpdateFilmeAction extends BaseAction<Filme> {

    /**
     * Construtor para a ação de atualizar um filme.
     *
     * @param validation A validação que será aplicada ao modelo de filme.
     */
    public UpdateFilmeAction(Validation<Filme> validation) {
        super(validation);
    }

    /**
     * Executa a ação de atualização de um filme.
     *
     * O método realiza a verificação de validade do modelo de filme antes de realizar
     * a atualização. Caso o modelo seja válido, o filme será atualizado no repositório.
     * Caso contrário, retorna um resultado de falha com a mensagem de erro correspondente.
     *
     * @param model O modelo de filme a ser atualizado.
     * @return O resultado da ação, indicando se a operação foi bem-sucedida ou não.
     */
    @Override
    public ActionResult execute(Filme model) {
        // Verifica se o modelo de filme é válido antes de prosseguir com a atualização
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        try {
            FilmeRepository.getInstance().update(model);
        } catch (Exception e) {
            return new ActionResult(false, "Erro! " + e.getMessage());
        }
        return new ActionResult(true,"Filme atualizado com sucesso");
    }
}
