package controller.action.genero;

import controller.dataBase.GeneroRepository;
import controller.action.Validation;
import model.Genero;

public class CreateGeneroValidation implements Validation<Genero> {
    private String errorMessage;

    @Override
    public boolean isValid(Genero model) {
        if (model.getNome() == null || model.getNome().isEmpty()) {
            errorMessage = "Erro! Nome do gênero não pode ser vazio";
            return false;
        }
        if (model.getNome().length() > 50) {
            errorMessage = "Erro! Nome do gênero não pode ter mais de 50 caracteres";
            return false;
        }
        if (model.getNome().length() < 3) {
            errorMessage = "Erro! Nome do gênero deve ter pelo menos 3 caracteres";
            return false;
        }
        GeneroRepository repository = GeneroRepository.getInstance();
        if (repository.getItems().stream().anyMatch(g -> g.getNome().equalsIgnoreCase(model.getNome()))) {
            errorMessage = "Erro! Gênero já existe na base de dados";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
