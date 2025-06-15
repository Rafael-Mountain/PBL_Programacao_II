package com.mountain_vd.controller.action.temporada;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Temporada;

public class CreateTemporadaValidation implements Validation<Temporada> {
    String errorMessage;

    @Override
    public boolean isValid(Temporada model) {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
