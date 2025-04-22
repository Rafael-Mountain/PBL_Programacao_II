package controller.action.livro;

import controller.action.Validation;
import controller.dataBase.FilmeRepository;
import controller.dataBase.LivroRepository;
import model.Livro;

public class CreateLivroValidation implements Validation<Livro> {
    private String errorMessage;

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
        LivroRepository repository = LivroRepository.getInstance();
        for (Livro l : repository.getItems()) {
            if (l.getIsbn().equals(model.getIsbn())) {
                errorMessage = "Erro! Já existe um livro com este ISBN na base de dados";
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
