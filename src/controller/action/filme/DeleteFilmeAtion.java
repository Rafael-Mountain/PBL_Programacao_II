package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Filme;

/**
 * Ação responsável pela exclusão de um filme no sistema.
 *
 * Esta classe extende {@link BaseAction} e implementa a lógica para remover um filme
 * do repositório, validando se a operação de exclusão pode ser realizada com base
 * na validação do modelo de filme.
 *
 * @see BaseAction
 * @see Validation
 * @see FilmeRepository
 * @see Filme
 */


public class DeleteFilmeAtion extends BaseAction<Filme> {
    /**
     * Construtor para a ação de deletar um filme.
     *
     * @param validation A validação que será aplicada ao modelo de filme.
     */
    public DeleteFilmeAtion(Validation<Filme> validation) {
        super(validation);
    }
    /**
     * Executa a ação de exclusão de um filme.
     *
     * O método realiza a verificação de validade do modelo de filme antes de realizar
     * a exclusão. Caso o modelo seja válido, o filme será removido do repositório.
     * Caso contrário, retorna um resultado de falha com a mensagem de erro correspondente.
     *
     * @param model O modelo de filme a ser deletado.
     * @return O resultado da ação, indicando se a operação foi bem-sucedida ou não.
     */
    @Override
    public ActionResult execute(Filme model) {
        // Verifica se o modelo de filme é válido antes de prosseguir com a exclusão
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        // Deleta o filme do repositório
        FilmeRepository.getInstance().delete(model);
        return new ActionResult(true,"Filme removido com sucesso");
    }
}
