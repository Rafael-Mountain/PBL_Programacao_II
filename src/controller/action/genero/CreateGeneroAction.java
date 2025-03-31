package controller.action.genero;

import controller.dataBase.GeneroRepository;
import controller.action.commons.ActionResult;
import controller.action.commons.BaseAction;
import controller.action.commons.Validation;
import model.Genero;

public class CreateGeneroAction extends BaseAction<Genero> {

    public CreateGeneroAction(Validation<Genero> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Genero model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }

        GeneroRepository.getInstance().save(model);
        return new ActionResult(true, "Gênero criado com sucesso");
    }
}
