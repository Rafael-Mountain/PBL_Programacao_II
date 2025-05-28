package controller.action.filme;

import controller.dataBase.FilmeRepository;
import controller.action.Validation;
import model.Filme;

/**
 * Validação para a criação de um filme no sistema.
 *
 * Esta classe implementa a interface {@link Validation} para garantir que um
 * filme esteja em conformidade com as regras de validação antes de ser salvo.
 * A validação inclui a verificação do título, dos gêneros associados ao filme
 * e se o filme já existe no repositório.
 *
 * @see Validation
 * @see FilmeRepository
 * @see Filme
 */
public class CreateFilmeValidation implements Validation<Filme> {
    private String errorMessage;

    /**
     * Valida as propriedades de um modelo de filme.
     *
     * As validações incluem:
     * 1. Verificar se o título do filme não está vazio ou nulo.
     * 2. Verificar se pelo menos um gênero foi selecionado para o filme.
     * 3. Verificar se o filme já existe no repositório.
     *
     * Caso alguma dessas condições não seja atendida, uma mensagem de erro será
     * definida e o método retorna {@code false}.
     *
     * @param model O modelo de filme a ser validado.
     * @return {@code true} se o filme for válido, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Filme model) {
        // Verifica se o título do filme não está vazio ou nulo
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! título vazio";
            return false;
        }

        // Verifica se pelo menos um gênero foi selecionado
        if (model.getGeneros().isEmpty()){
            errorMessage = "Erro! Selecione pelo menos um gênero";
            return false;
        }

        // Verifica se o filme já existe no repositório
        FilmeRepository repository = FilmeRepository.getInstance();
        if(repository.getItems().contains(model)){
            errorMessage = "Erro! Filme já existe na base de dados";
            return false;
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro associada à validação.
     *
     * @return A mensagem de erro se a validação falhar, ou {@code null} se não houver erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
