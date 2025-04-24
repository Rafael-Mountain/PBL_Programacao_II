package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import model.Filme;

public class UpdateFilmeAction extends BaseAction<Filme> {

    public UpdateFilmeAction(Validation<Filme> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Filme model) {
        if(!isValid(model)){
            return new ActionResult(false, getErrorMessage());
        }
        try {
            FilmeRepository.getInstance().update(model);
        } catch (Exception e) {
            return new ActionResult(false, "Erro! " + e.getMessage());
        }
        return new ActionResult(true,"Filme atualizado com sucesso");
    }
}
