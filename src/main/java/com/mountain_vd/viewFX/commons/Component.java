package com.mountain_vd.viewFX.commons;

import javafx.scene.Node;

/**
 * Interface que representa um componente visual genérico no sistema.
 * <p>
 * Herda de {@link Displayable}, garantindo que todo componente também possa ser exibido
 * ou tenha uma representação visual definida.
 * </p>
 *
 * <p>
 * O objetivo desta interface é padronizar o acesso ao elemento gráfico principal
 * ({@link javafx.scene.Node}) que representa o componente na interface JavaFX.
 * </p>
 *
 */
public interface Component extends Displayable {

    /**
     * Retorna o nó raiz associado a este componente.
     * Este nó pode ser adicionado diretamente a outras estruturas de layout
     * ou à cena principal da aplicação.
     *
     * @return {@link javafx.scene.Node} que representa visualmente este componente
     */
    Node getNode();
}
