package com.mountain_vd.controller.action.genero;

import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Genero;

/**
 * Validação para a criação de um novo gênero no sistema.
 *
 * Esta classe implementa a interface {@link Validation} e realiza a validação
 * de um modelo {@link Genero} para garantir que ele atenda aos requisitos antes
 * de ser persistido. A validação inclui checagens para garantir que o nome do
 * gênero não seja vazio, tenha um comprimento adequado e que o gênero não exista
 * previamente na base de dados.
 *
 * @see Validation
 * @see Genero
 */
public class CreateGeneroValidation implements Validation<Genero> {
    private String errorMessage;

    /**
     * Valida o modelo de gênero.
     *
     * A validação verifica os seguintes critérios:
     * 1. O nome do gênero não pode ser vazio.
     * 2. O nome do gênero não pode ter mais de 50 caracteres.
     * 3. O nome do gênero deve ter pelo menos 3 caracteres.
     * 4. O gênero não pode existir na base de dados.
     *
     * Caso algum critério falhe, é armazenada uma mensagem de erro que pode
     * ser recuperada posteriormente com o método {@link #getErrorMessage}.
     *
     * @param model O modelo {@link Genero} a ser validado.
     * @return {@code true} se o modelo for válido, {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Genero model) {
        // Valida se o nome do gênero está vazio
        if (model.getNome() == null || model.getNome().isEmpty()) {
            errorMessage = "Erro! Nome do gênero não pode ser vazio";
            return false;
        }

        // Valida se o nome do gênero tem mais de 50 caracteres
        if (model.getNome().length() > 50) {
            errorMessage = "Erro! Nome do gênero não pode ter mais de 50 caracteres";
            return false;
        }

        // Valida se o nome do gênero tem pelo menos 3 caracteres
        if (model.getNome().length() < 3) {
            errorMessage = "Erro! Nome do gênero deve ter pelo menos 3 caracteres";
            return false;
        }

        // Valida se o gênero já existe na base de dados
        GeneroRepository repository = GeneroRepository.getInstance();
        if (repository.getItems().stream().anyMatch(g -> g.getNome().equalsIgnoreCase(model.getNome()))) {
            errorMessage = "Erro! Gênero já existe na base de dados";
            return false;
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro que descreve o motivo da falha na validação.
     *
     * @return A mensagem de erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
