package controller.action.filme;

import controller.action.Validation;
import model.Filme;

/**
 * Validação para a atualização de um filme no sistema.
 *
 * Esta classe implementa a interface {@link Validation} para validar um modelo de
 * filme antes de realizar sua atualização. Ela garante que o título do filme não
 * seja vazio e que o filme tenha pelo menos um gênero associado.
 *
 * @see Validation
 * @see Filme
 */
public class UpdateFilmeValidation implements Validation<Filme> {
    private String errorMessage;

    /**
     * Valida as informações do filme para garantir que o título e os gêneros estejam
     * corretamente definidos antes da atualização.
     *
     * A validação verifica que o título do filme não é nulo nem vazio e que pelo
     * menos um gênero foi selecionado para o filme. Se algum desses requisitos
     * não for atendido, o erro correspondente é gerado.
     *
     * @param model O modelo de filme a ser validado.
     * @return {@code true} se o filme for válido para atualização, {@code false}
     *         caso contrário.
     */
    @Override
    public boolean isValid(Filme model) {
        // Verifica se o título do filme é válido (não nulo e não vazio)
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! título vazio";
            return false;
        }

        // Verifica se o filme possui pelo menos um gênero
        if (model.getGeneros().isEmpty()) {
            errorMessage = "Erro! Selecione pelo menos um gênero";
            return false;
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro gerada durante a validação.
     *
     * @return A mensagem de erro caso a validação falhe, ou {@code null} se a validação
     *         for bem-sucedida.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
