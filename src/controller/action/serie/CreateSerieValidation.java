package controller.action.serie;

import controller.action.commons.Validation;
import controller.dataBase.SerieRepository;
import model.Filme;
import model.Serie;

public class CreateSerieValidation implements Validation<Serie> {
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
        SerieRepository repository = SerieRepository.getInstance();
        if(repository.getItems().contains(model)){
            errorMessage = "Erro! Serie ja existe na base de dados";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
