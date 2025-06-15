package com.mountain_vd.controller.action;

/**
 * Interface que define uma ação a ser executada sobre um modelo.
 *
 * Esta interface representa uma ação genérica que pode ser aplicada a qualquer tipo de modelo. A ação executa um processo sobre o modelo fornecido e retorna um resultado de ação.
 *
 * @param <T> O tipo de modelo que a ação irá processar.
 *
 * @see ActionResult
 */
public interface Action<T> {

    /**
     * Executa a ação sobre o modelo fornecido.
     *
     * Este método deve ser implementado por classes que definem ações específicas a serem realizadas sobre um modelo.
     * O modelo é passado como parâmetro, e o método retorna um resultado de ação que pode indicar sucesso ou falha.
     *
     * @param model O modelo sobre o qual a ação será executada.
     * @return O resultado da execução da ação, que pode indicar sucesso ou falha.
     */
    ActionResult execute(T model);
}
