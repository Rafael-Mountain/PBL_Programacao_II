package com.mountain_vd.viewFX;

import com.mountain_vd.model.Genero;
import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.GenrePickerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GenrePickerPane implements Component {
    private VBox vbox;
    private ObservableList<Genero> generos;
    private TextField genreField;
    private GenrePickerController controller;


    public GenrePickerPane() {
        generos = FXCollections.observableArrayList();
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox(10);

        genreField = new TextField();
        genreField.setPromptText("Coloque o gênero");
        HBox.setHgrow(genreField, Priority.ALWAYS);

        Button botaoAdicionar = new Button("Adicionar");
        botaoAdicionar.setOnAction(e -> {
            GenrePickerController.addGenre(genreField.getText(), generos);
        });

        HBox hbox = new HBox(10, genreField, botaoAdicionar);
        vbox.getChildren().addAll(hbox, renderListView());
    }

    public ListView<Genero> renderListView() {
        ListView<Genero> listView = new ListView<>(generos);

        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Genero item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox hbox = new HBox(10);
                    Label label = new Label(item.getNome());

                    // Caminhos das imagens
                    Image normalImage = new Image(getClass().getResource("/images/close.png").toExternalForm());
                    Image hoverImage = new Image(getClass().getResource("/images/closeBold.png").toExternalForm());

                    ImageView imageView = new ImageView(normalImage);
                    imageView.setFitWidth(16);
                    imageView.setFitHeight(16);

                    Button removeButton = new Button();
                    removeButton.setGraphic(imageView);
                    removeButton.setStyle("-fx-background-color: transparent; -fx-padding: 4;");

                    removeButton.setOnMouseEntered(e -> imageView.setImage(hoverImage));
                    removeButton.setOnMouseExited(e -> imageView.setImage(normalImage));

                    removeButton.setOnAction(event -> generos.remove(item));

                    hbox.getChildren().addAll(label, removeButton);
                    setGraphic(hbox);
                }
            }
        });

        return listView;
    }
}
