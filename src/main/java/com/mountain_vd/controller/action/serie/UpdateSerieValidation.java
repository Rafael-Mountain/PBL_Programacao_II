package com.mountain_vd.controller.action.serie;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Serie;

/**
 * Validação para a atualização de uma série no sistema.
 *
 * Esta classe realiza a validação dos dados fornecidos para uma série antes de permitir sua atualização.
 * A validação assegura que o título da série não esteja vazio e que pelo menos um gênero esteja selecionado.
 * Caso qualquer uma dessas condições falhe, a validação retornará uma mensagem de erro apropriada.
 *
 * @see Validation
 * @see Serie
 */
public class UpdateSerieValidation implements Validation<Serie> {
    private String errorMessage;

    /**
     * Valida os dados fornecidos para a série antes da atualização.
     *
     * A validação garante que o título da série não esteja vazio e que ao menos um gênero seja selecionado.
     * Se alguma dessas condições falhar, a validação retornará uma mensagem de erro específica.
     *
     * @param model A série a ser validada.
     * @return `true` se a série for válida, `false` caso contrário.
     */
    @Override
    public boolean isValid(Serie model) {
        if (model.getTitulo() == null || model.getTitulo().isEmpty()) {
            errorMessage = "Erro! título vazio";
            return false;
        }

        if (model.getGeneros().isEmpty()) {
            errorMessage = "Erro! Selecione pelo menos um gênero";
            return false;
        }

        return true;
    }

    /**
     * Retorna a mensagem de erro associada à validação.
     *
     * Esta mensagem será retornada se a validação falhar, e indica o motivo do erro na série fornecida.
     *
     * @return A mensagem de erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
