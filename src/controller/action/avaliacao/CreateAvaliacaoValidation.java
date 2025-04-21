package controller.action.avaliacao;

import controller.action.Validation;
import model.Avaliacao;

public class CreateAvaliacaoValidation implements Validation<Avaliacao> {
    String errorMessage;

    @Override
    public boolean isValid(Avaliacao model) {
        if (model.getPontuacao() < 1 || model.getPontuacao() > 5 ) {
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}
