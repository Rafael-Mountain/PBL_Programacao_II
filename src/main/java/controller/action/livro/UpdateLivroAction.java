package controller.action.livro;

import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import controller.dataBase.LivroRepository;
import model.Livro;

/**
 * Ação para atualizar um livro no sistema.
 *
 * Esta classe representa a ação que realiza a atualização de um livro no repositório
 * de livros. A validação do livro é realizada antes da atualização. Se o livro for válido,
 * ele será atualizado no repositório de livros.
 *
 * @see BaseAction
 * @see Validation
 * @see Livro
 * @see LivroRepository
 */
public class UpdateLivroAction extends BaseAction<Livro> {

    /**
     * Constrói uma instância de {@link UpdateLivroAction} com a validação fornecida.
     *
     * @param validation A validação a ser aplicada ao livro antes da atualização.
     */
    public UpdateLivroAction(Validation<Livro> validation) {
        super(validation);
    }

    /**
     * Executa a ação de atualização de um livro.
     *
     * Este método valida o livro com a validação fornecida. Se o livro for válido, ele
     * será atualizado no repositório de livros. Caso contrário, um erro será retornado
     * com a respectiva mensagem de falha na validação.
     *
     * @param model O modelo {@link Livro} a ser atualizado.
     * @return Um {@link ActionResult} indicando se a operação foi bem-sucedida ou falhou.
     */
    @Override
    public ActionResult execute(Livro model) {
        // Verifica se o modelo é válido antes de proceder
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        try {
            LivroRepository.getInstance().update(model);
        } catch (Exception e) {
            return new ActionResult(false, "Erro! " + e.getMessage());
        }
        return new ActionResult(true, "Livro atualizado com sucesso");
    }
}

