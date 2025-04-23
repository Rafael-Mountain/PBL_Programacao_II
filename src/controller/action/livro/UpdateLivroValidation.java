package controller.action.livro;

import controller.action.Validation;
import model.Livro;

public class UpdateLivroValidation implements Validation<Livro> {
    String errorMessage;

    @Override
    public boolean isValid(Livro model) {
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
