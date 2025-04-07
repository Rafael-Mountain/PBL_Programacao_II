package controller.action.temporada;

import controller.action.BaseSetAction;
import controller.dataBase.SerieRepository;
import controller.action.ActionResult;
import controller.action.Validation;
import model.Temporada;
import model.Serie;

public class CreateTemporadaAction extends BaseSetAction<Temporada, Serie> {

    public CreateTemporadaAction(Validation<Temporada> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Temporada model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        SerieRepository repository= SerieRepository.getInstance();
        model.setSerieId(superModel.getId());
        superModel.updateTemporadaById(model.getId(), model);
        repository.update(superModel);


        return new ActionResult(true, "Temporada created successfully");
    }
}
