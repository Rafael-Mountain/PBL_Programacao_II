package com.mountain_vd.controller.action.temporada;

import com.mountain_vd.controller.action.BaseSetAction;
import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.model.Serie;

public class CreateTemporadaAction extends BaseSetAction<Temporada, Serie> {

    public CreateTemporadaAction(Validation<Temporada> validation) {
        super(validation);
    }

    @Override
    public ActionResult execute(Temporada model) {
        if (superModel == null){
            return new ActionResult(false, "Set super model");
        }

        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }

        SerieRepository repository= SerieRepository.getInstance();
        superModel.addTemporada(model);
        repository.update(superModel);

        return new ActionResult(true, "Temporada created successfully");
    }
}
