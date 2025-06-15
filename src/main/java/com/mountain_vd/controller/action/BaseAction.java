package com.mountain_vd.controller.action;

/**
 * Representa a ação base que executa uma operação sobre um modelo genérico.
 *
 * A classe `BaseAction` implementa a interface `Action` e fornece a funcionalidade básica para validar modelos antes de executar uma ação. Ela utiliza um mecanismo de validação, que é passado como parâmetro no construtor, para garantir que as condições necessárias sejam atendidas antes de realizar a ação.
 *
 * As subclasses de `BaseAction` devem fornecer a implementação específica de como a ação será executada no modelo.
 *
 * @param <T> O tipo de modelo sobre o qual a ação será executada.
 */
public abstract class BaseAction<T> implements Action<T> {

    /**
     * O validador utilizado para validar o modelo antes de realizar a ação.
     */
    protected final Validation<T> validation;

    /**
     * Constrói uma nova instância de `BaseAction` com o validador fornecido.
     *
     * @param validation O validador a ser utilizado para validar o modelo antes de executar a ação.
     */
    public BaseAction(Validation<T> validation) {
        this.validation = validation;
    }

    /**
     * Valida o modelo fornecido utilizando o validador.
     *
     * @param model O modelo a ser validado.
     * @return `true` se o modelo for válido, caso contrário `false`.
     */
    protected boolean isValid(T model) {
        return validation.isValid(model);
    }

    /**
     * Obtém a mensagem de erro associada à falha de validação, caso o modelo não seja válido.
     *
     * @return A mensagem de erro explicando por que o modelo é inválido.
     */
    protected String getErrorMessage() {
        return validation.getErrorMessage();
    }
}
