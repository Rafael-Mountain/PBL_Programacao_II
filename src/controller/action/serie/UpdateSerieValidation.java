package controller.action.serie;

import controller.action.Validation;
import model.Serie;

public class UpdateSerieValidation implements Validation<Serie> {
    String errorMessage;

    @Override
    public boolean isValid(Serie model) {
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! titulo vazio";
            return false;
        }

        if (model.getGeneros().isEmpty()){
            errorMessage = "Erro! Selecione pelo menos um genero";
            return false;
        }

        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
