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

public class CreateMediaPane implements Component {
    RootScene rootScene;
    CreateMediaController controller;
    VBox container;


    public CreateMediaPane(RootScene rootScene, CreateMediaController controller ) {
        this.controller = controller;
        this.rootScene = rootScene;
        render();
    }

    @Override
    public Node getNode() {
        VBox.setVgrow(container, Priority.ALWAYS);
        VBox wrapper = new VBox(container);
        wrapper.setPadding(new Insets(5)); // padding efetivo aqui
        return wrapper;
    }

    @Override
    public void render() {
        container = new VBox(5);
        Node form = controller.getForm();

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

        container.getChildren().addAll(form,spacer,buttonDiv, footerPadding);
    }
}
