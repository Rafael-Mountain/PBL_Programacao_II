package controller.action.serie;

import controller.action.Validation;
import controller.dataBase.SerieRepository;
import model.Serie;

/**
 * Validação para criação de uma nova série no sistema.
 *
 * Esta classe é responsável por validar as informações de uma série antes de ser criada.
 * Ela verifica se os campos obrigatórios estão preenchidos e se a série não existe
 * já na base de dados. Caso a série não seja válida, retorna uma mensagem de erro apropriada.
 *
 * @see Validation
 * @see Serie
 * @see SerieRepository
 */
public class CreateSerieValidation implements Validation<Serie> {

    private String errorMessage;

    /**
     * Verifica a validade do modelo de série fornecido.
     *
     * Esta validação inclui a verificação se o título da série não é vazio, se pelo menos
     * um gênero foi selecionado e se a série não existe previamente no repositório de séries.
     *
     * @param model O objeto {@link Serie} a ser validado.
     * @return {@code true} se a série for válida, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Serie model) {
        // Verifica se o título da série está vazio
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! titulo vazio";
            return false;
        }

        // Verifica se a série possui pelo menos um gênero
        if (model.getGeneros().isEmpty()) {
            errorMessage = "Erro! Selecione pelo menos um genero";
            return false;
        }

        // Verifica se a série já existe na base de dados
        SerieRepository repository = SerieRepository.getInstance();
        if (repository.getItems().contains(model)) {
            errorMessage = "Erro! Serie ja existe na base de dados";
            return false;
        }

        if(repository.getItems().stream().anyMatch(serie -> serie.getTitulo().equals(model.getTitulo()))){
            errorMessage = "Erro! Serie ja existe na base de dados";
            return false;
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro associada à validação.
     *
     * Caso a série não seja válida, esta mensagem descreve o motivo do erro.
     *
     * @return A mensagem de erro, caso existam falhas na validação.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
