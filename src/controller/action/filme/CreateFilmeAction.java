package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Filme;

/**
 * Ação responsável pela criação de um novo filme no sistema.
 *
 * Esta classe estende {@link BaseAction} e implementa a lógica de criação de
 * um filme, validando o modelo e, em seguida, salvando-o no repositório de filmes.
 * Caso o filme seja inválido, a ação retornará uma mensagem de erro.
 *
 * @see BaseAction
 * @see FilmeRepository
 * @see ActionResult
 * @see Validation
 */
public class CreateFilmeAction extends BaseAction<Filme> {

    /**
     * Construtor da classe CreateFilmeAction.
     *
     * Este construtor inicializa a ação com a validação fornecida para
     * o modelo de filme.
     *
     * @param validation A validação a ser aplicada ao modelo de filme antes
     *                   de sua criação.
     */
    public CreateFilmeAction(Validation<Filme> validation) {
        super(validation);
    }

    /**
     * Executa a ação de criação de um filme no sistema.
     *
     * A ação valida o modelo de filme utilizando a validação fornecida. Se o
     * modelo for válido, o filme é salvo no repositório de filmes. Caso contrário,
     * é retornado um resultado de erro com a mensagem de erro apropriada.
     *
     * @param filme O modelo de filme a ser criado.
     * @return Um {@link ActionResult} indicando se a criação do filme foi
     *         bem-sucedida ou não, juntamente com uma mensagem de sucesso ou erro.
     */
    @Override
    public ActionResult execute(Filme filme) {
        // Verifica se o modelo é válido
        if (!isValid(filme)) {
            return new ActionResult(false, getErrorMessage());
        }

        FilmeRepository.getInstance().add(filme);
        return new ActionResult(true,"Filme criado com sucesso");
    }
}
