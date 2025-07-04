package com.mountain_vd.controller.action.avaliacao;

import com.mountain_vd.controller.action.Validation;
import com.mountain_vd.model.Avaliacao;

/**
 * Implementação da interface {@link Validation} para validar objetos do tipo {@link Avaliacao}.
 *
 * <p>Esta classe verifica se a pontuação atribuída à avaliação está dentro de um intervalo válido (entre 1 e 5, inclusive).</p>
 *
 * <p>Se a validação falhar, a ação que utiliza esta validação poderá acessar a mensagem de erro por meio do método {@link #getErrorMessage()}.</p>
 *
 * @author [Seu Nome]
 */
public class CreateAvaliacaoValidation implements Validation<Avaliacao> {

    /** Mensagem de erro que descreve a falha de validação, caso ocorra. */
    private String errorMessage;

    /**
     * Verifica se o objeto {@link Avaliacao} é válido.
     *
     * <p>Apenas avaliações com pontuação entre 1 e 5 são consideradas válidas.</p>
     *
     * @param model O objeto {@link Avaliacao} a ser validado.
     * @return {@code true} se a avaliação for válida; {@code false} caso contrário.
     */
    @Override
    public boolean isValid(Avaliacao model) {
        if (model == null) {
            errorMessage = "Avaliacao está nula";
            return false;
        }
        if (model.getDataConsumo() == null){
            errorMessage = "Erro! Coloque uma data valida";
            return false;
        }

        if (model.getPontuacao() < 1 || model.getPontuacao() > 5) {
            errorMessage = "Pontuação inválida: deve estar entre 1 e 5.";
            return false;
        }

        // Adicione outras validações se necessário
        errorMessage = null;
        return true;
    }

    /**
     * Retorna a mensagem de erro associada à última falha de validação.
     *
     * @return Uma string contendo a mensagem de erro, ou {@code null} se não houve erro.
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}
