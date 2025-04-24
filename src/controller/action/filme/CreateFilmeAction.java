package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Filme;

public class CreateFilmeAction extends BaseAction<Filme> {

    public CreateFilmeAction(Validation<Filme> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Filme filme) {
        if (!isValid(filme)) {
            return new ActionResult(false, getErrorMessage());
        }

        FilmeRepository.getInstance().add(filme);
        return new ActionResult(true,"Filme criado com sucesso");
    }
}
