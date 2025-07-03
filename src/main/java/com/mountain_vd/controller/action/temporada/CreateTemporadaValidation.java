package com.mountain_vd.controller.action.temporada;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Temporada;

public class CreateTemporadaValidation implements Validation<Temporada> {
    String errorMessage;

    @Override
    public boolean isValid(Temporada model) {
        if (model == null) {
            errorMessage = "Temporada não pode ser nula.";
            return false;
        }
        if (model.getqEpisodios() <= 0) {
            errorMessage = "Erro! A temporada deve ter pelo menos 1 episódio.";
            return false;
        }
        if (model.getAno() == null ||  model.getAno().getYear() <= 0) {
            errorMessage = "Erro! a temporada deve ter um ano válido.";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
