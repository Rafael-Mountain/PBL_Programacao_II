package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.CreateMediaController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Componente JavaFX que representa a interface para criação de uma mídia.
 * Encapsula o formulário da mídia e os botões de ação (Salvar e Cancelar).
 * Utiliza um controlador que implementa a interface CreateMediaController para gerenciar a lógica.
 */
public class CreateMediaPane implements Component {
    /** Referência à cena raiz da aplicação. */
    RootScene rootScene;

    /** Controlador da mídia para tratar ações do formulário. */
    CreateMediaController controller;

    /** Container principal do layout. */
    VBox container;

    /**
     * Construtor que inicializa o componente com a cena principal e o controlador.
     *
     * @param rootScene Cena raiz da aplicação para interação com UI.
     * @param controller Controlador que gerencia o formulário e ações.
     */
    public CreateMediaPane(RootScene rootScene, CreateMediaController controller ) {
        this.controller = controller;
        this.rootScene = rootScene;
        render();
    }

    /**
     * Retorna o nó JavaFX que representa o componente completo.
     *
     * @return Nó VBox contendo o formulário e botões.
     */
    @Override
    public Node getNode() {
        VBox.setVgrow(container, Priority.ALWAYS);
        VBox wrapper = new VBox(container);
        wrapper.getStyleClass().add("media-container");
        wrapper.setPadding(new Insets(5)); // padding efetivo aqui
        return wrapper;
    }

    /**
     * Renderiza o conteúdo do componente, criando o formulário e os botões.
     * Define o layout, adiciona espaçadores e configura os eventos dos botões.
     */
    @Override
    public void render() {
        container = new VBox(5);
        Node form = controller.getForm();
        container.getStyleClass().add("media-content");

        HBox buttonDiv = new HBox(10);
        buttonDiv.setAlignment(Pos.CENTER_RIGHT);

        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(event -> controller.save());

        Button cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction(event -> controller.returnPage());

        buttonDiv.getChildren().addAll(salvarButton, cancelarButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Region footerPadding = new Region();
        footerPadding.setPrefHeight(10);

        container.getChildren().addAll(form, spacer, buttonDiv, footerPadding);
    }
}