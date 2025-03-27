package controller.action.Filme;

import controller.DataBase.FilmeRepository;
import controller.action.commons.Validation;
import model.Filme;

public class UpdateFilmValidation implements Validation<Filme> {
    private String errorMessage;

    @Override
    public boolean isValid(Filme model) {
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "titulo vazio";
            return false;
        }

        if (model.getGeneros().isEmpty()){
            errorMessage = "Selecione pelo menos um genero";
            return false;
        }

        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
