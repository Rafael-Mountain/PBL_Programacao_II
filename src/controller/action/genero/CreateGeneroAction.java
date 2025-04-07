package controller.action.genero;

import controller.dataBase.GeneroRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
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
