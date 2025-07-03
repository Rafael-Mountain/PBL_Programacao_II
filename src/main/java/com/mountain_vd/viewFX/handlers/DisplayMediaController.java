package com.mountain_vd.viewFX.handlers;

import javafx.scene.Node;

/**
 * Classe abstrata que define os métodos essenciais para controlar a exibição e atualização
 * de uma mídia no sistema.
 * Subclasses devem implementar a criação do formulário, a lógica de atualização e o retorno à página anterior.
 */
public abstract class DisplayMediaController {

    /**
     * Retorna o nó JavaFX que representa o formulário de exibição/edição da mídia.
     *
     * @return o nó que representa o formulário
     */
    public abstract Node getForm();

    /**
     * Executa a ação de atualização dos dados da mídia exibida.
     */
    public abstract void update();

    /**
     * Retorna para a página anterior ou principal após a operação de atualização.
     */
    public abstract void returnPage();
}
