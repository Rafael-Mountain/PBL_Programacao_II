package controller.action.serie;

import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
import controller.dataBase.SerieRepository;
import model.Serie;

public class UpdateSerieAction extends BaseAction<Serie> {

    public UpdateSerieAction(Validation<Serie> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Serie model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        try{
            SerieRepository.getInstance().update(model);
        } catch (Exception e) {
            return new ActionResult(false, "Erro! " + e.getMessage());
        }

        return new ActionResult(true, "Serie atualizado com sucesso");
    }
}
