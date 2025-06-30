package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DisplayMidiaPane implements Component {
    RootScene rootScene;
    DisplayMediaController controller;
    VBox container;


    public DisplayMidiaPane(DisplayMediaController controller, RootScene rootScene) {
        this.controller = controller;
        this.rootScene = rootScene;
    }


    @Override
    public Node getNode() {
        return container;
    }

    @Override
    public void render() {
        container = new VBox(10);
        Node form = controller.getForm();

        HBox buttonDiv = new HBox(10);
        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(event -> {controller.update();});

        Button deletarButton = new Button("Deletar");
        deletarButton.setOnAction( event -> { controller.delete();});

        Button cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction(event ->{controller.returnPage();});
        buttonDiv.getChildren().addAll(salvarButton, cancelarButton);


        container.getChildren().addAll(form,buttonDiv);

    }
}
