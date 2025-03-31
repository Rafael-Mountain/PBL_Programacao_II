package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.commons.ActionResult;
import controller.action.commons.BaseAction;
import controller.action.commons.Validation;
import model.Filme;

public class DeleteFilmeAtion extends BaseAction<Filme> {

    public DeleteFilmeAtion(Validation<Filme> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Filme model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        FilmeRepository.getInstance().delete(model);
        return new ActionResult(true,"Filme removido com sucesso");
    }
}
