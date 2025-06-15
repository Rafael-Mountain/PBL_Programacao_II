package com.mountain_vd.controller.action.livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.BaseAction;
import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.Livro;

/**
 * Ação para criar um novo livro no sistema.
 *
 * Esta classe implementa a ação de criar um novo livro no sistema. Ela valida
 * o modelo de livro antes de persistir no repositório de livros. Se o modelo
 * for válido, o livro é salvo no banco de dados, caso contrário, uma mensagem
 * de erro é retornada.
 *
 * @see BaseAction
 * @see Livro
 * @see LivroRepository
 */
public class CreateLivroAction extends BaseAction<Livro> {

    /**
     * Constrói uma nova ação de criação de livro com a validação fornecida.
     *
     * @param validation A validação a ser utilizada para verificar o modelo de livro.
     */
    public CreateLivroAction(Validation<Livro> validation) {
        super(validation);
    }

    /**
     * Executa a ação de criação de um livro. Valida o modelo e, se válido,
     * persiste o livro no repositório. Caso contrário, retorna uma mensagem de erro.
     *
     * @param livro O modelo {@link Livro} a ser criado.
     * @return O resultado da ação, indicando sucesso ou falha, junto com a mensagem correspondente.
     */
    @Override
    public ActionResult execute(Livro livro) {
        // Verifica se o modelo é válido
        if (!isValid(livro)) {
            return new ActionResult(false, getErrorMessage());
        }

        LivroRepository.getInstance().add(livro);
        return new ActionResult(true,"Livro criado com sucesso");
    }
}
