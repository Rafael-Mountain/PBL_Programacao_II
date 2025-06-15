package com.mountain_vd.controller.action;

/**
 * Representa uma ação base para modelos com um supermodelo adicional.
 *
 * A classe `BaseSetAction` é uma extensão de `BaseAction` e adiciona a capacidade de associar um supermodelo ao modelo principal da ação. Esta classe é útil quando a ação precisa de um contexto adicional ou um modelo de nível superior para operar junto ao modelo principal.
 *
 * As subclasses de `BaseSetAction` devem fornecer a implementação específica de como a ação será executada no modelo principal.
 *
 * @param <T> O tipo de modelo principal sobre o qual a ação será executada.
 * @param <M> O tipo de supermodelo que é associado ao modelo principal.
 */
public abstract class BaseSetAction<T, M> extends BaseAction<T> {

    /**
     * O supermodelo associado ao modelo principal para a execução da ação.
     */
    protected M superModel;

    /**
     * Constrói uma nova instância de `BaseSetAction` com o validador fornecido.
     *
     * @param validation O validador a ser utilizado para validar o modelo principal antes de executar a ação.
     */
    public BaseSetAction(Validation<T> validation) {
        super(validation);
    }

    /**
     * Define o supermodelo que será associado ao modelo principal.
     *
     * @param superModel O supermodelo a ser associado ao modelo principal.
     */
    public void setSuperModel(M superModel) {
        this.superModel = superModel;
    }

}
