package controller.action.serie;

import controller.dataBase.SerieRepository;
import controller.action.commons.ActionResult;
import controller.action.commons.BaseAction;
import controller.action.commons.Validation;
import model.Serie;

public class CreateSerieAction extends BaseAction<Serie> {
    public CreateSerieAction(Validation validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Serie serie) {
        if (!isValid(serie)) {
            return new ActionResult(false, getErrorMessage());
        }

        SerieRepository.getInstance().save(serie);
        return new ActionResult(true, "Série criada com sucesso");
    }
}
