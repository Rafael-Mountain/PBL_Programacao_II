package com.mountain_vd.controller.action;

/**
 * Interface que define a validação de modelos para ações.
 *
 * A interface `Validation` é usada para validar um modelo antes de realizar uma ação sobre ele. A validação permite garantir que o modelo esteja em um estado adequado antes de ser processado, evitando erros ou inconsistências durante a execução da ação.
 *
 * As classes que implementam esta interface devem fornecer a lógica de validação específica para o modelo que estão tratando.
 *
 * @param <T> O tipo de modelo a ser validado.
 */
public interface Validation<T> {

    /**
     * Valida o modelo fornecido.
     *
     * Este método deve ser implementado para verificar se o modelo está em um estado válido para a execução da ação. O que constitui um estado válido depende da implementação concreta da validação.
     *
     * @param model O modelo a ser validado.
     * @return `true` se o modelo for válido, caso contrário `false`.
     */
    boolean isValid(T model);

    /**
     * Retorna a mensagem de erro associada à validação.
     *
     * Se o modelo não for válido, este método deve retornar uma mensagem explicativa do erro. A mensagem pode ser usada para informar o usuário sobre o que está errado com o modelo.
     *
     * @return A mensagem de erro, ou uma string vazia se não houver erro.
     */
    String getErrorMessage();
}