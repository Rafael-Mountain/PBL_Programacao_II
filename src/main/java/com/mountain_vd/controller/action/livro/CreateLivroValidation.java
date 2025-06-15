package com.mountain_vd.controller.action.livro;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.Livro;

/**
 * Validação para a criação de um novo livro no sistema.
 *
 * Esta classe implementa a interface {@link Validation} e realiza a validação
 * de um modelo de livro antes de sua criação. Ela verifica se o título do livro
 * não está vazio, se o livro possui pelo menos um gênero associado, e se o
 * ISBN do livro não existe previamente na base de dados.
 *
 * @see Validation
 * @see Livro
 * @see LivroRepository
 */
public class CreateLivroValidation implements Validation<Livro> {
    private String errorMessage;

    /**
     * Valida o modelo de livro. Verifica se o título do livro está vazio, se ele
     * possui pelo menos um gênero associado e se o ISBN já existe na base de dados.
     *
     * @param model O modelo {@link Livro} a ser validado.
     * @return {@code true} se o modelo de livro for válido, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Livro model) {
        // Verifica se o título do livro não está vazio
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! titulo vazio";
            return false;
        }

        // Verifica se o livro possui pelo menos um gênero
        if (model.getGeneros().isEmpty()) {
            errorMessage = "Erro! Selecione pelo menos um genero";
            return false;
        }

        // Verifica se o ISBN já existe na base de dados
        LivroRepository repository = LivroRepository.getInstance();
        for (Livro l : repository.getItems()) {
            if (l.getIsbn().equals(model.getIsbn())) {
                errorMessage = "Erro! Já existe um livro com este ISBN na base de dados";
                return false;
            }
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro associada à falha de validação, caso haja.
     *
     * @return A mensagem de erro, ou {@code null} se não houver erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
