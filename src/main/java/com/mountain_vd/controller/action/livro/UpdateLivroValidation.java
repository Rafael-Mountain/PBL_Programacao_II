package com.mountain_vd.controller.action.livro;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Livro;

/**
 * Validação para a atualização de um livro no sistema.
 *
 * Esta classe implementa a interface {@link Validation} para validar as informações
 * de um livro antes de sua atualização. Ela verifica se o título do livro não está vazio
 * e se ao menos um gênero foi selecionado.
 *
 * @see Validation
 * @see Livro
 */
public class UpdateLivroValidation implements Validation<Livro> {
    private String errorMessage;

    /**
     * Valida as informações de um livro para garantir que sejam corretas antes da atualização.
     *
     * A validação garante que:
     * 1. O título do livro não pode ser nulo ou vazio.
     * 2. O livro deve ter pelo menos um gênero selecionado.
     *
     * @param model O modelo {@link Livro} a ser validado.
     * @return {@code true} se o livro for válido para atualização, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Livro model) {
        // Verifica se o título do livro não está vazio ou nulo
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! titulo vazio";
            return false;
        }

        // Verifica se o livro possui ao menos um gênero
        if (model.getGeneros().isEmpty()){
            errorMessage = "Erro! Selecione pelo menos um genero";
            return false;
        }

        // Se as condições forem atendidas, o livro é considerado válido
        return true;
    }

    /**
     * Retorna a mensagem de erro gerada durante a validação do livro.
     *
     * @return A mensagem de erro caso a validação falhe, ou {@code null} se não houver erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
