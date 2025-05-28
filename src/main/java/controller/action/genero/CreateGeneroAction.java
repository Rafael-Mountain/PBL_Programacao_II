package controller.action.genero;

import controller.dataBase.GeneroRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Genero;

public class CreateGeneroAction extends BaseAction<Genero> {

    /**
     * Construtor que recebe a validação do modelo {@link Genero}.
     *
     * @param validation A validação do modelo {@link Genero}.
     */
    public CreateGeneroAction(Validation<Genero> validation) {
        super(validation);
    }

    /**
     * Executa a criação de um novo gênero no sistema.
     *
     * A operação primeiro valida o modelo de gênero. Se o modelo for válido,
     * o gênero é salvo no repositório. Caso contrário, retorna um resultado de
     * erro com a mensagem de erro da validação.
     *
     * @param model O modelo {@link Genero} a ser criado.
     * @return Um objeto {@link ActionResult} indicando o sucesso ou falha da operação.
     */
    @Override
    public ActionResult execute(Genero model) {
        // Valida o modelo antes de salvar
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }

        GeneroRepository.getInstance().add(model);
        return new ActionResult(true, "Gênero criado com sucesso");
    }
}
