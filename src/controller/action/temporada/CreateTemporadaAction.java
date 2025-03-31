package controller.action.temporada;

import controller.dataBase.SerieRepository;
import controller.action.commons.ActionResult;
import controller.action.commons.BaseAction;
import controller.action.commons.Validation;
import model.Temporada;
import model.Serie;

public class CreateTemporadaAction extends BaseAction<Temporada> {

    public CreateTemporadaAction(Validation<Temporada> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Temporada model) {
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }
        SerieRepository repository= SerieRepository.getInstance();
        Serie serie = repository.getItemById(model.getIdSerie());
        serie.updateTemporadaById(model.getId(), model);
        repository.update(serie);


        return new ActionResult(true, "Temporada created successfully");
    }
}
