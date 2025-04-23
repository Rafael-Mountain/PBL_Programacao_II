package controller.action.livro;

import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import controller.dataBase.LivroRepository;
import model.Livro;

public class UpdateLivroAction extends BaseAction<Livro> {

    public UpdateLivroAction(Validation<Livro> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Livro model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }

        LivroRepository.getInstance().update(model);
        return new ActionResult(true, "Livro atualizado com sucesso");
    }
}

