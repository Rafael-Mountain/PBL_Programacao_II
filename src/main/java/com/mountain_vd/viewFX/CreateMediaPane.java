package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class CreateMediaPane implements Component {
    VBox vbox;

    public CreateMediaPane(RootScene rootScene) {
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox(10);

        GenrePickerPane genrePickerPane = new GenrePickerPane();

        vbox.getChildren().add(genrePickerPane.getNode());

        // Aqui você pode adicionar outros componentes, como campos de entrada, botões, etc.
        // Exemplo:
        // TextField titleField = new TextField();
        // titleField.setPromptText("Título do Mídia");
        // vbox.getChildren().add(titleField);

        // Adicione mais componentes conforme necessário
    }
}
