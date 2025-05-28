package controller.action.temporada;

import controller.action.Validation;
import model.Temporada;

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
