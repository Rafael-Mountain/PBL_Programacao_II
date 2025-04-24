package controller.action.avaliacao;

import controller.action.Validation;
import model.Avaliacao;

public class CreateAvaliacaoValidation implements Validation<Avaliacao> {
    private String errorMessage;

    @Override
    public boolean isValid(Avaliacao model) {
        if (model == null) {
            errorMessage = "Avaliacao está nula";
            return false;
        }

        if (model.getPontuacao() < 1 || model.getPontuacao() > 5) {
            errorMessage = "Pontuação inválida: deve estar entre 1 e 5.";
            return false;
        }

        // Adicione outras validações se necessário
        errorMessage = null;
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
