package controller.action.serie;

import controller.dataBase.SerieRepository;
import controller.action.ActionResult;
import controller.action.BaseAction;
import controller.action.Validation;
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

        SerieRepository.getInstance().add(serie);
        return new ActionResult(true, "Série criada com sucesso");
    }
}
