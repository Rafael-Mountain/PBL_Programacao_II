package com.mountain_vd.viewFX.handlers;

import javafx.scene.Node;

/**
 * Interface que define os métodos necessários para controlar a criação de uma mídia.
 * Implementações devem prover o formulário para cadastro, salvar os dados e retornar à página anterior.
 */
public interface CreateMediaController {

    /**
     * Retorna o nó JavaFX que representa o formulário de criação da mídia.
     *
     * @return o nó que representa o formulário
     */
    public abstract Node getForm();

    /**
     * Executa a ação de salvar os dados da mídia criada.
     */
    public abstract void save();

    /**
     * Retorna para a página anterior ou principal após a operação de criação.
     */
    public abstract void returnPage();

}
