package controller.action.serie;

import controller.dataBase.SerieRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Serie;

/**
 * Ação para criar uma nova série no sistema.
 *
 * Esta classe é responsável por receber as informações de uma série, validá-las
 * e, caso válidas, salvar a série no repositório. Ela estende a classe {@link BaseAction}
 * e implementa a lógica necessária para criar uma nova série.
 *
 * @see Validation
 * @see Serie
 * @see SerieRepository
 */
public class CreateSerieAction extends BaseAction<Serie> {

    /**
     * Construtor da classe {@link CreateSerieAction}.
     *
     * Inicializa a ação de criação de série com a validação fornecida.
     *
     * @param validation A validação que será aplicada à série antes de ser criada.
     */
    public CreateSerieAction(Validation<Serie> validation) {
        super(validation);
    }

    /**
     * Executa a ação de criação de uma nova série.
     *
     * Este método verifica a validade do modelo de série fornecido. Se o modelo for válido,
     * a série é salva no repositório de séries. Caso contrário, retorna um resultado de falha com a
     * mensagem de erro apropriada.
     *
     * @param serie O objeto {@link Serie} a ser criado.
     * @return O resultado da ação, que contém o status da operação e a mensagem associada.
     */
    @Override
    public ActionResult execute(Serie serie) {
        // Verifica se a série é válida antes de salvar
        if (!isValid(serie)) {
            return new ActionResult(false, getErrorMessage());
        }

        SerieRepository.getInstance().add(serie);
        return new ActionResult(true, "Série criada com sucesso");
    }
}
