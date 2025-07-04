package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Componente JavaFX que representa a interface para exibição e edição de uma mídia existente.
 * Exibe o formulário preenchido e permite salvar alterações ou voltar para a tela anterior.
 * Utiliza um controlador que implementa DisplayMediaController para controlar as ações.
 */
public class DisplayMediaPane implements Component {
    /** Cena raiz da aplicação para interação com a interface. */
    RootScene rootScene;

    /** Controlador que gerencia a lógica do formulário de exibição. */
    DisplayMediaController controller;

    /** Container principal do layout da interface. */
    VBox container;

    /**
     * Construtor que inicializa o componente com o controlador e a cena raiz.
     *
     * @param controller Controlador responsável pelas ações da mídia exibida.
     * @param rootScene Cena raiz da aplicação para controle da interface.
     */
    public DisplayMediaPane(DisplayMediaController controller, RootScene rootScene) {
        this.controller = controller;
        this.rootScene = rootScene;
        render();
    }

    /**
     * Retorna o nó JavaFX que representa o componente completo da interface.
     *
     * @return Nó VBox contendo o formulário e os botões de ação.
     */
    @Override
    public Node getNode() {
        VBox.setVgrow(container, Priority.ALWAYS);
        VBox wrapper1 = new VBox(container);
        wrapper1.getStyleClass().add("media-content");
        wrapper1.setPadding(new Insets(10));
        VBox wrapper2 = new VBox(wrapper1);
        wrapper2.getStyleClass().add("media-container");
        return wrapper2;
    }

    /**
     * Renderiza o conteúdo da interface, incluindo o formulário,
     * os botões Salvar e Voltar, além dos espaçadores para layout.
     */
    @Override
    public void render() {
        container = new VBox();
        Node form = controller.getForm();

        HBox buttonDiv = new HBox(10);
        buttonDiv.setAlignment(Pos.CENTER_RIGHT);

        Button salvarButton = new Button("Salvar");
        salvarButton.getStyleClass().add("style-button");
        salvarButton.setOnAction(event -> {controller.update();});

        Button cancelarButton = new Button("Voltar");
        cancelarButton.getStyleClass().add("style-button");
        cancelarButton.setOnAction(event -> {controller.returnPage();});

        buttonDiv.getChildren().addAll(salvarButton, cancelarButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        container.getChildren().addAll(form, spacer, buttonDiv);
    }
}
