package controller.action.filme;

import controller.action.Validation;
import model.Filme;

public class UpdateFilmValidation implements Validation<Filme> {
    private String errorMessage;

    @Override
    public boolean isValid(Filme model) {
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
